package com.mjc.school.service.interfaces;

import com.mjc.school.repository.model.impl.Role;
import com.mjc.school.service.dto.RoleDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;


@Component
@Mapper(componentModel = "spring")
public interface RoleModelMapper {

    RoleDto modelToDto(Role role);
    Role dtoToModel(RoleDto roleDto);

    List<RoleDto> roleListToDtoList(List<Role> entities);

    Set<Role> dtoRolesToRoleModels(Set<RoleDto> roleDtoList);

    default Page<RoleDto> rolePageToDtoPage(Page<Role> page){
        return page.map(this::modelToDto);
    }
}
