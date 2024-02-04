package com.mjc.school.service.implementation;

import com.mjc.school.exception.EntityConflictRepositoryException;
import com.mjc.school.model.impl.TagModel;
import com.mjc.school.interfaces.TagRepository;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.*;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.interfaces.TagModelMapper;
import com.mjc.school.service.validator.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.exceptions.ServiceErrorCode.TAG_CONFLICT;
import static com.mjc.school.service.exceptions.ServiceErrorCode.TAG_ID_DOES_NOT_EXIST;

@Service
public class TagService implements
        BaseService<TagDtoRequest, TagDtoResponse, Long, TagModel, TagDtoRequest> {

    private final TagRepository tagRepository;
    private final TagModelMapper mapper;

    @Autowired
    public TagService(TagRepository tagRepository, TagModelMapper tagModelMapper) {
        this.tagRepository = tagRepository;
        this.mapper = tagModelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public PageDtoResponse<TagDtoResponse> readAll(ResourceSearchFilterRequestDTO searchFilterRequestDTO, Pageable pageable) {
        Page<TagModel> page;
        if (searchFilterRequestDTO != null) {
            page = tagRepository.findAll(getSpecification(searchFilterRequestDTO), pageable);

        } else {
            page = tagRepository.findAll(pageable);
        }
        Page<TagDtoResponse> result = mapper.tagPageToDtoPage(page);
        return new PageDtoResponse<>(result.getContent(), result.getPageable().getPageNumber(), result.getTotalPages());
    }

    @Override
    @Transactional(readOnly = true)
    public TagDtoResponse readById(Long id) {
        Optional<TagModel> result = tagRepository.findById(id);
        return mapper.modelToDto(result.orElseThrow(() -> new NotFoundException(
                String.format(String.valueOf(TAG_ID_DOES_NOT_EXIST.getMessage()), id))));
    }

    @Override
    @Transactional
    public TagDtoResponse create(@Valid TagDtoRequest createRequest) {
        try {
            TagModel model = mapper.dtoToModel(createRequest);
            model = tagRepository.save(model);
            return mapper.modelToDto(model);
        } catch (EntityConflictRepositoryException ex) {
            throw new ResourceConflictServiceException(TAG_CONFLICT.getMessage(), TAG_CONFLICT.getErrorCode(), ex.getMessage());
        }
    }

    @Override
    @Transactional
    public TagDtoResponse update(Long id, @Valid TagDtoRequest updateRequest) {
        if (tagRepository.existsById(id)) {
            TagModel model = tagRepository.findById(id).get();
            model.setName(updateRequest.name());
            model = tagRepository.save(model);
            return mapper.modelToDto(model);
        } else {
            throw new NotFoundException(String.format(TAG_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format(TAG_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Transactional(readOnly = true)
    public List<TagDtoResponse> readByNewsId(Long newsId) {
        return mapper.modelListToDtoList(tagRepository.findByNewsModelsId(newsId));
    }
}
