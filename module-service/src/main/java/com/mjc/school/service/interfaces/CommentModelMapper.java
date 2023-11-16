package com.mjc.school.service.interfaces;

import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.repository.interfaces.NewsRepository;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.dto.CommentsDtoForNewsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = NewsModelMapper.class)
public abstract class CommentModelMapper {
    @Autowired
    protected NewsRepository newsRepository;

    @Mapping(target = "newsId", expression = "java(comment.getNewsModel().getId())")
    @Mapping(target = "createdDate", source = "created")
    @Mapping(target = "lastModifiedDate", source = "modified")
    public abstract CommentDtoResponse modelToDto(Comment comment);

    public Page<CommentDtoResponse> commentPageToDtoPage(Page<Comment> commentModelPage){
        return commentModelPage.map(this::modelToDto);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    @Mapping(target = "newsModel", expression = "java(newsRepository.getReferenceById(commentDtoRequest.newsId()))")
    public abstract Comment dtoToModel(CommentDtoRequest commentDtoRequest);

    public abstract List<CommentDtoResponse> modelListToDtoList(List<Comment> comments);

    @Mapping(source = "created", target = "createdDate")
    @Mapping(source = "modified", target = "lastUpdatedDate")
    public abstract CommentsDtoForNewsResponse modelToDtoForNews(Comment model);
}
