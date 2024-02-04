package com.mjc.school.service.implementation;

import com.mjc.school.exception.EntityConflictRepositoryException;
import com.mjc.school.model.impl.AuthorModel;
import com.mjc.school.interfaces.AuthorRepository;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.PageDtoResponse;
import com.mjc.school.service.dto.ResourceSearchFilterRequestDTO;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.interfaces.AuthorModelMapper;
import com.mjc.school.service.validator.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

@Service
public class AuthorService implements
        BaseService<AuthorDtoRequest, AuthorDtoResponse, Long, AuthorModel, AuthorDtoRequest> {

    private final AuthorRepository authorRepository;
    private final AuthorModelMapper mapper;


    @Autowired
    public AuthorService(AuthorModelMapper authorModelMapper,
                         AuthorRepository authorRepository) {
        this.mapper = authorModelMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public PageDtoResponse<AuthorDtoResponse> readAll(ResourceSearchFilterRequestDTO searchFilterRequestDTO, Pageable pageable) {
        Page<AuthorModel> page;
        if (searchFilterRequestDTO != null) {
            page = authorRepository.findAll(getSpecification(searchFilterRequestDTO), pageable);

        } else {
            page = authorRepository.findAll(pageable);
        }
        Page<AuthorDtoResponse> result = mapper.authorPageToDtoPage(page);
        return new PageDtoResponse<>(result.getContent(), result.getPageable().getPageNumber(), result.getTotalPages());
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDtoResponse readById(Long authorId) {
        Optional<AuthorModel> authorModel = authorRepository.findById(authorId);
        if (authorModel.isPresent()) {
            return mapper.modelToDto(authorModel.get());
        } else {
            throw new NotFoundException(String.format(String.valueOf(AUTHOR_ID_DOES_NOT_EXIST.getMessage()), authorId));
        }
    }

    @Override
    @Transactional
    public AuthorDtoResponse create(@Valid AuthorDtoRequest createAuthorRequest) {
        try {
            AuthorModel model = mapper.dtoToModel(createAuthorRequest);
            model = authorRepository.save(model);
            return mapper.modelToDto(model);
        } catch (EntityConflictRepositoryException ex) {
            throw new ResourceConflictServiceException(AUTHOR_CONFLICT.getMessage(), AUTHOR_CONFLICT.getErrorCode(), ex.getMessage());
        }
    }

    @Override
    @Transactional
    public AuthorDtoResponse update(Long id, @Valid AuthorDtoRequest updateAuthorRequest) {
        if (authorRepository.existsById(id)) {
            AuthorModel updateModel = authorRepository.findById(id).get();
            AuthorModel model = mapper.dtoToModel(updateAuthorRequest);
            updateModel.setId(id);
            updateModel.setName(model.getName());
            return mapper.modelToDto(authorRepository.save(updateModel));
        } else {
            throw new NotFoundException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (authorRepository.existsById(id)) {
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
