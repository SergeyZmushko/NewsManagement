package com.mjc.school.service.implementation;

import com.mjc.school.repository.exception.EntityConflictRepositoryException;
import com.mjc.school.repository.filter.pagination.Page;
import com.mjc.school.repository.implementation.CommentRepository;
import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.dto.PageDtoResponse;
import com.mjc.school.service.dto.ResourceSearchFilterRequestDTO;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.filter.ResourceSearchFilter;
import com.mjc.school.service.filter.mapper.CommentsSearchFilterMapper;
import com.mjc.school.service.interfaces.CommentModelMapper;
import com.mjc.school.service.validator.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

@Service
public class CommentService implements
        BaseService<CommentDtoRequest, CommentDtoResponse, Long, ResourceSearchFilterRequestDTO, CommentDtoRequest> {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final CommentModelMapper commentModelMapper;
    private final CommentsSearchFilterMapper commentsSearchFilterMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentModelMapper commentModelMapper,
                          NewsRepository newsRepository, CommentsSearchFilterMapper commentsSearchFilterMapper) {
        this.commentRepository = commentRepository;
        this.commentModelMapper = commentModelMapper;
        this.newsRepository = newsRepository;
        this.commentsSearchFilterMapper = commentsSearchFilterMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public PageDtoResponse<CommentDtoResponse> readAll(@Valid ResourceSearchFilterRequestDTO searchFilterRequest) {
        final ResourceSearchFilter searchFilter = commentsSearchFilterMapper.map(searchFilterRequest);
        final Page<Comment> page = commentRepository.readAll(getEntitySearchSpecification(searchFilter));
        final List<CommentDtoResponse> modelDtoList = commentModelMapper.modelListToDtoList(page.entities());
        return new PageDtoResponse<>(modelDtoList, page.currentPage(), page.pageCount());
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDtoResponse readById(@Valid Long id) {
        Optional<Comment> comment = commentRepository.readById(id);
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
        if(!newsRepository.existById(createRequest.newsId())){
            throw new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), createRequest.newsId()));
        }
        try {
            Comment model = commentModelMapper.dtoToModel(createRequest);
            model = commentRepository.create(model);
            return commentModelMapper.modelToDto(model);
        }catch (EntityConflictRepositoryException ex){
            throw new ResourceConflictServiceException(COMMENT_CONFLICT.getMessage(), COMMENT_CONFLICT.getErrorCode(), ex.getMessage());
        }
        }


    @Override
    @Transactional
    public CommentDtoResponse update(Long id, @Valid CommentDtoRequest updateRequest) {
        if (commentRepository.existById(id)) {
            Comment model = commentModelMapper.dtoToModel(updateRequest);
            model.setId(id);
            model = commentRepository.update(model);
            return commentModelMapper.modelToDto(model);
        } else {
            throw new NotFoundException(String.format(COMMENT_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (commentRepository.existById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format(COMMENT_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Transactional(readOnly = true)
    public List<CommentDtoResponse> readByNewsId(Long newsId) {
        return commentModelMapper.modelListToDtoList(commentRepository.readCommentsByNewsId(newsId));
    }
}
