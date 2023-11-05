package com.mjc.school.service.implementation;

import com.mjc.school.repository.exception.EntityConflictRepositoryException;
import com.mjc.school.repository.filter.pagination.Page;
import com.mjc.school.repository.implementation.AuthorRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.repoV2Implementation.AuthorRepositoryNew;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.PageDtoResponse;
import com.mjc.school.service.dto.ResourceSearchFilterRequestDTO;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.filter.ResourceSearchFilter;
import com.mjc.school.service.filter.mapper.AuthorSearchFilterMapper;
import com.mjc.school.service.interfaces.AuthorModelMapper;
import com.mjc.school.service.validator.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

@Service
public class AuthorService implements
        BaseService<AuthorDtoRequest, AuthorDtoResponse, Long, ResourceSearchFilterRequestDTO, AuthorDtoRequest> {

    private final AuthorRepositoryNew authorRepositoryNew;
    private final AuthorRepository authorRepository;
    private final AuthorModelMapper mapper;
    private final AuthorSearchFilterMapper authorSearchFilterMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository,
                         AuthorModelMapper authorModelMapper, AuthorSearchFilterMapper authorSearchFilterMapper, AuthorRepositoryNew authorRepositoryNew) {
        this.authorRepository = authorRepository;
        this.mapper = authorModelMapper;
        this.authorSearchFilterMapper = authorSearchFilterMapper;
        this.authorRepositoryNew = authorRepositoryNew;
    }

    @Transactional(readOnly = true)
    public AuthorDtoResponse readById1(Long authorId){
        Optional<AuthorModel> authorModel = authorRepositoryNew.findById(authorId);
        if (authorModel.isPresent()){
            return mapper.modelToDto(authorModel.get());
        } else {
            throw new NotFoundException(String.format(String.valueOf(AUTHOR_ID_DOES_NOT_EXIST.getMessage()), authorId));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PageDtoResponse<AuthorDtoResponse> readAll(@Valid ResourceSearchFilterRequestDTO searchFilterRequest) {
        final ResourceSearchFilter searchFilter = authorSearchFilterMapper.map(searchFilterRequest);
        final Page<AuthorModel> page = authorRepository.readAll(getEntitySearchSpecification(searchFilter));
        final List<AuthorDtoResponse> modelDtoList = mapper.authorListToDtoList(page.entities());
        return new PageDtoResponse<>(modelDtoList, page.currentPage(), page.pageCount());
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDtoResponse readById(Long authorId) {
        Optional<AuthorModel> authorModel = authorRepository.readById(authorId);
        if (authorModel.isPresent()) {
            return mapper.modelToDto(authorModel.get());
        } else {
            throw new NotFoundException(
                    String.format(String.valueOf(AUTHOR_ID_DOES_NOT_EXIST.getMessage()), authorId));
        }
    }

    @Override
    @Transactional
    public AuthorDtoResponse create(@Valid AuthorDtoRequest createAuthorRequest) {
        try {
            AuthorModel model = mapper.dtoToModel(createAuthorRequest);
            model = authorRepository.create(model);
            return mapper.modelToDto(model);
        } catch (EntityConflictRepositoryException ex) {
            throw new ResourceConflictServiceException(AUTHOR_CONFLICT.getMessage(), AUTHOR_CONFLICT.getErrorCode(), ex.getMessage());
        }
    }

    @Override
    @Transactional
    public AuthorDtoResponse update(Long id, @Valid AuthorDtoRequest updateAuthorRequest) {
        if (authorRepository.existById(id)) {
            AuthorModel model = mapper.dtoToModel(updateAuthorRequest);
            model.setId(id);
            model = authorRepository.update(model);
            return mapper.modelToDto(model);
        } else {
            throw new NotFoundException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (authorRepository.existById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    public AuthorDtoResponse readByNewsId(Long newsId) {
        return authorRepository.readByNewsId(newsId)
                .map(mapper::modelToDto)
                .orElseThrow(
                        () -> new NotFoundException(String.format(AUTHOR_DOES_NOT_EXIST_FOR_NEWS_ID.getMessage(), newsId))
                );
    }
}
