package com.mjc.school.service.interfaces;

import com.mjc.school.model.impl.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring")
public interface TagModelMapper {
    List<TagDtoResponse> modelListToDtoList(List<TagModel> modelList);

    @Mapping(target = "id", source = "id")
    TagDtoResponse modelToDto(TagModel model);


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "newsModels", ignore = true)
    })
    TagModel dtoToModel(TagDtoRequest dto);

    default Page<TagDtoResponse> tagPageToDtoPage(Page<TagModel> page){
        return page.map(this::modelToDto);
    }
}
