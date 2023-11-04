package com.mjc.school.service.interfaces;

import com.mjc.school.repository.model.impl.UserModel;
import com.mjc.school.service.dto.SignUpDtoRequest;
import com.mjc.school.service.dto.UserDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UserModelMapper {

    @Mapping(target = "token", ignore = true)
    UserDtoResponse modelToDto(UserModel userModel);


    UserModel dtoToModel(SignUpDtoRequest signUpDtoRequest);

    List<UserDtoResponse> userListToDtoList(List<UserModel> entities);
}
