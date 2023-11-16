package com.mjc.school.service.interfaces;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@Component
public interface AuthorModelMapper {
    default Page<AuthorDtoResponse> authorPageToDtoPage(Page<AuthorModel> authorModelPage){
        return authorModelPage.map(this::modelToDto);
    }

    List<AuthorDtoResponse> authorListToDtoList(List<AuthorModel> authorModelList);


    AuthorDtoResponse modelToDto(AuthorModel model);

    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdDate", ignore = true)
//    @Mapping(target = "lastUpdatedDate", ignore = true)
    @Mapping(target = "news", ignore = true)
    AuthorModel dtoToModel(AuthorDtoRequest dto);


}
