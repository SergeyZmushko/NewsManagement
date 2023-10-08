package com.mjc.school.service.interfaces;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.implementation.AuthorRepository;
import com.mjc.school.repository.implementation.TagRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.dto.CommentsDtoForNewsResponse;
import com.mjc.school.service.dto.CreateNewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.UpdateNewsDtoRequest;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {AuthorModelMapper.class, TagModelMapper.class, CommentModelMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public abstract class NewsModelMapper {
    @Autowired
    protected AuthorRepository authorRepository;
    @Autowired
    protected TagRepository tagRepository;
    @Autowired
    protected BaseRepository<Comment, Long> commentsRepository;
    @Autowired
    protected CommentModelMapper commentMapper;

    public abstract List<NewsDtoResponse> modelListToDtoList(List<NewsModel> modelList);

    @Mapping(source = "authorModel", target = "authorDto")
    @Mapping(source = "tagModels", target = "tagsDto")
    @Mapping(target = "commentsDto", ignore = true)
    @Mapping(target = "createdDate", source = "createDate" )
    @Mapping(target = "lastUpdatedDate", source = "lastUpdateDate")
    public abstract NewsDtoResponse modelToDto(NewsModel model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "authorModel", expression =
            "java(authorRepository.readByName(dto.author()).get())")
    @Mapping(target = "tagModels", expression =
            "java(dto.tags().stream().map(name -> tagRepository.readByName(name).get()).toList())")
    @Mapping(target = "comments", expression =
            "java(dto.commentsIds().stream().map(commentId -> commentsRepository.getReference(commentId)).toList())")
    public abstract NewsModel dtoToModel(CreateNewsDtoRequest dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "authorModel", ignore = true)
    @Mapping(target = "tagModels", expression =
            "java(dto.tags().stream().map(name -> tagRepository.readByName(name).get()).toList())")
    @Mapping(target = "comments", expression =
            "java(dto.commentsIds().stream().map(commentId -> commentsRepository.getReference(commentId)).toList())")
    public abstract NewsModel dtoToModel(UpdateNewsDtoRequest dto);

    @AfterMapping
    void setAuthor(UpdateNewsDtoRequest updateRequest, @MappingTarget NewsModel model){
        if (updateRequest.author() != null && !updateRequest.author().isBlank()){
            AuthorModel authorModel = null;
            Optional<AuthorModel> authorModelOptional = authorRepository.readByName(updateRequest.author());
            if (authorModelOptional.isPresent()){
                authorModel = authorModelOptional.get();
            }
            model.setAuthorModel(authorModel);
        }
    }

    @AfterMapping
    void setComments(NewsModel model, @MappingTarget NewsDtoResponse dto){
        if (model.getComments() != null){
            List<CommentsDtoForNewsResponse> commentsDto = model.getComments().stream()
                    .map(c -> commentMapper.modelToDtoForNews(c))
                    .toList();
            dto.setCommentsDto(commentsDto);
        }
    }
}
