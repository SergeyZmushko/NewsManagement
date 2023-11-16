package com.mjc.school.service.implementation;

import com.mjc.school.repository.exception.EntityConflictRepositoryException;
import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.repository.interfaces.CommentRepository;
import com.mjc.school.repository.interfaces.NewsRepository;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.dto.PageDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.interfaces.CommentModelMapper;
import com.mjc.school.service.validator.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

@Service
public class CommentService implements
        BaseService<CommentDtoRequest, CommentDtoResponse, Long, Integer, CommentDtoRequest> {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final CommentModelMapper commentModelMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentModelMapper commentModelMapper,
                          NewsRepository newsRepository) {
        this.commentRepository = commentRepository;
        this.commentModelMapper = commentModelMapper;
        this.newsRepository = newsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public PageDtoResponse<CommentDtoResponse> readAll(Integer pageNo, Integer pageSize, String sort) {
        String[] sortParams = sort.split(":");
        Sort.Direction direction = sortParams[1].equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortParams[0]));

        Page<Comment> page = commentRepository.findAll(pageRequest);
        Page<CommentDtoResponse> result = commentModelMapper.commentPageToDtoPage(page);
        return new PageDtoResponse<>(result.getContent(), result.getPageable().getPageNumber(), result.getTotalPages());
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDtoResponse readById(@Valid Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return commentModelMapper.modelToDto(comment.get());
        } else {
            throw new NotFoundException(
                    String.format(String.valueOf(COMMENT_ID_DOES_NOT_EXIST.getMessage()), id));
        }
    }

    @Override
    @Transactional
    public CommentDtoResponse create(@Valid CommentDtoRequest createRequest) {
        if(!newsRepository.existsById(createRequest.newsId())){
            throw new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), createRequest.newsId()));
        }
        try {
            Comment model = commentModelMapper.dtoToModel(createRequest);
            model = commentRepository.save(model);
            return commentModelMapper.modelToDto(model);
        }catch (EntityConflictRepositoryException ex){
            throw new ResourceConflictServiceException(COMMENT_CONFLICT.getMessage(), COMMENT_CONFLICT.getErrorCode(), ex.getMessage());
        }
        }


    @Override
    @Transactional
    public CommentDtoResponse update(Long id, @Valid CommentDtoRequest updateRequest) {
        if (commentRepository.existsById(id)) {
            Comment result = commentRepository.findById(id).get();
            result.setNewsModel(newsRepository.findById(updateRequest.newsId()).get());
            result.setContent(updateRequest.content());
            result.setModified(LocalDateTime.now());
            return commentModelMapper.modelToDto(commentRepository.save(result));
        } else {
            throw new NotFoundException(String.format(COMMENT_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format(COMMENT_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Transactional(readOnly = true)
    public List<CommentDtoResponse> readByNewsId(Long newsId) {
        return commentModelMapper.modelListToDtoList(commentRepository.findByNewsModelId(newsId));
    }
}
