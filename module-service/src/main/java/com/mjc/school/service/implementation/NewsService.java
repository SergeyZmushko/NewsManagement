package com.mjc.school.service.implementation;

import com.mjc.school.exception.EntityConflictRepositoryException;
import com.mjc.school.model.impl.AuthorModel;
import com.mjc.school.model.impl.NewsModel;
import com.mjc.school.model.impl.TagModel;
import com.mjc.school.interfaces.AuthorRepository;
import com.mjc.school.interfaces.NewsRepository;
import com.mjc.school.interfaces.TagRepository;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.*;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.interfaces.NewsModelMapper;
import com.mjc.school.service.validator.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

@Service
public class NewsService implements
        BaseService<CreateNewsDtoRequest, NewsDtoResponse, Long, NewsModel, UpdateNewsDtoRequest> {
    private final NewsRepository newsRepository;
    private final AuthorRepository authorRepository;
    private final TagRepository tagRepository;
    private final NewsModelMapper mapper;

    @Autowired
    public NewsService(NewsRepository newsRepository, AuthorRepository authorRepository,
                       TagRepository tagRepository, NewsModelMapper mapper) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
        this.tagRepository = tagRepository;
        this.mapper = mapper;

    }

    @Override
    @Transactional(readOnly = true)
    public PageDtoResponse<NewsDtoResponse> readAll(ResourceSearchFilterRequestDTO searchFilterRequestDTO, Pageable pageable) {
        Page<NewsModel> page;
        if (searchFilterRequestDTO != null) {
            page = newsRepository.findAll(getSpecification(searchFilterRequestDTO), pageable);

        } else {
            page = newsRepository.findAll(pageable);
        }
        Page<NewsDtoResponse> result = mapper.newsPageToDtoPage(page);
        return new PageDtoResponse<>(result.getContent(), result.getPageable().getPageNumber(), result.getTotalPages());

    }

    @Override
    @Transactional(readOnly = true)
    public NewsDtoResponse readById(Long newsId) {
        Optional<NewsModel> result = newsRepository.findById(newsId);
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
            model = newsRepository.save(model);
            return mapper.modelToDto(model);
        } catch (EntityConflictRepositoryException ex) {
            throw new ResourceConflictServiceException(NEWS_CONFLICT.getMessage(), NEWS_CONFLICT.getErrorCode(), ex.getMessage());
        }
    }

    @Override
    @Transactional
    public NewsDtoResponse update(Long id, @Valid UpdateNewsDtoRequest updateRequest) {
        NewsModel model = newsRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), id)));
        createNonExistentAuthor(updateRequest.author());
        createNonExistentTags(updateRequest.tags());
        model.setTitle(updateRequest.title());
        model.setContent(updateRequest.content());
        AuthorModel authorModel = new AuthorModel();
        authorModel.setName(updateRequest.author());
        model.setAuthorModel(authorModel);
        model.setTagModels(updateRequest.tags()
                .stream()
                .map(tag ->
                        tagRepository.findByName(tag).orElseThrow(() ->
                                new NotFoundException(String.format(TAG_ID_DOES_NOT_EXIST.getMessage(), tag))))
                .collect(Collectors.toList()));
        model.setLastUpdateDate(LocalDateTime.now());

        model = newsRepository.save(model);
        return mapper.modelToDto(model);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), id));
        }

    }

    private void createNonExistentAuthor(String authorName) {
        if (authorName != null && !authorName.isEmpty()) {
            if (authorRepository.findByName(authorName).isEmpty()) {
                AuthorModel author = new AuthorModel();
                author.setName(authorName);
                authorRepository.save(author);
            }
        }
    }

    private void createNonExistentTags(List<String> tagNames) {
        tagNames.stream()
                .filter(name -> tagRepository.findByName(name).isEmpty())
                .map(name -> {
                    TagModel tag = new TagModel();
                    tag.setName(name);
                    return tag;
                })
                .forEach(tagRepository::save);
    }
}
