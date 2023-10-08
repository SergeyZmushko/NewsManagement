package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.exception.EntityConflictRepositoryException;
import com.mjc.school.repository.filter.pagination.Page;
import com.mjc.school.repository.implementation.AuthorRepository;
import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.implementation.TagRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.*;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.filter.ResourceSearchFilter;
import com.mjc.school.service.filter.mapper.NewsSearchFilterMapper;
import com.mjc.school.service.interfaces.NewsModelMapper;
import com.mjc.school.service.validator.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

@Service
public class NewsService implements
        BaseService<CreateNewsDtoRequest, NewsDtoResponse, Long, ResourceSearchFilterRequestDTO, UpdateNewsDtoRequest> {
    private final BaseRepository<NewsModel, Long> newsRepository;
    private final AuthorRepository authorRepository;
    private final TagRepository tagRepository;
    private final NewsModelMapper mapper;
    private final NewsSearchFilterMapper newsSearchFilterMapper;


    @Autowired
    public NewsService(NewsRepository newsRepository, AuthorRepository authorRepository,
                       TagRepository tagRepository, NewsModelMapper mapper,
                       NewsSearchFilterMapper newsSearchFilterMapper) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
        this.tagRepository = tagRepository;
        this.mapper = mapper;
        this.newsSearchFilterMapper = newsSearchFilterMapper;

    }

    @Override
    @Transactional(readOnly = true)
    public PageDtoResponse<NewsDtoResponse> readAll(@Valid ResourceSearchFilterRequestDTO searchFilterRequest) {
        final ResourceSearchFilter searchFilter = newsSearchFilterMapper.map(searchFilterRequest);
        Page<NewsModel> page = newsRepository.readAll(getEntitySearchSpecification(searchFilter));
        List<NewsDtoResponse> modelDtoList = mapper.modelListToDtoList(page.entities());
        return new PageDtoResponse<>(modelDtoList, page.currentPage(), page.pageCount());
    }

    @Override
    @Transactional(readOnly = true)
    public NewsDtoResponse readById(Long newsId) {
        Optional<NewsModel> result = newsRepository.readById(newsId);
        return mapper.modelToDto(result.orElseThrow(() -> new NotFoundException(
                String.format(String.valueOf(NEWS_ID_DOES_NOT_EXIST.getMessage()), newsId))));
    }

    @Override
    @Transactional
    public NewsDtoResponse create(@Valid CreateNewsDtoRequest createRequest) {
        createNonExistentAuthor(createRequest.author());
        createNonExistentTags(createRequest.tags());
        try {
            NewsModel model = mapper.dtoToModel(createRequest);
            model = newsRepository.create(model);
            return mapper.modelToDto(model);
        }catch (EntityConflictRepositoryException ex){
            throw new ResourceConflictServiceException(NEWS_CONFLICT.getMessage(), NEWS_CONFLICT.getErrorCode(), ex.getMessage());
        }
    }

    @Override
    @Transactional
    public NewsDtoResponse update(Long id, @Valid UpdateNewsDtoRequest updateRequest) {
        if (!newsRepository.existById(id)) {
            throw new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), id));
        }
        createNonExistentAuthor(updateRequest.author());
        createNonExistentTags(updateRequest.tags());

        NewsModel model = mapper.dtoToModel(updateRequest);
        model.setId(id);
        model = newsRepository.update(model);
        return mapper.modelToDto(model);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (newsRepository.existById(id)) {
            newsRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), id));
        }

    }

    private void createNonExistentAuthor(String authorName) {
        if (authorName != null && !authorName.equals("")) {
            if (authorRepository.readByName(authorName).isEmpty()) {
                AuthorModel author = new AuthorModel();
                author.setName(authorName);
                authorRepository.create(author);
            }
        }
    }

    private void createNonExistentTags(List<String> tagNames) {
        tagNames.stream()
                .filter(name -> tagRepository.readByName(name).isEmpty())
                .map(name -> {
                    TagModel tag = new TagModel();
                    tag.setName(name);
                    return tag;
                })
                .forEach(tagRepository::create);
    }
}
