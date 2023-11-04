package com.mjc.school.service.implementation;

import com.mjc.school.repository.exception.EntityConflictRepositoryException;
import com.mjc.school.repository.implementation.RoleRepositoryImpl;
import com.mjc.school.repository.filter.pagination.Page;
import com.mjc.school.repository.model.impl.Role;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.PageDtoResponse;
import com.mjc.school.service.dto.ResourceSearchFilterRequestDTO;
import com.mjc.school.service.dto.RoleDto;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.filter.ResourceSearchFilter;
import com.mjc.school.service.filter.mapper.RoleSearchFilterMapper;
import com.mjc.school.service.interfaces.RoleModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

@Service
public class RoleService implements BaseService<RoleDto, RoleDto, Long, ResourceSearchFilterRequestDTO, RoleDto> {

    private final RoleRepositoryImpl repository;
    private final RoleModelMapper roleModelMapper;

    private final RoleSearchFilterMapper roleSearchFilterMapper;

    @Autowired
    public RoleService(RoleRepositoryImpl repository, RoleModelMapper roleModelMapper, RoleSearchFilterMapper roleSearchFilterMapper){
        this.repository = repository;
        this.roleModelMapper = roleModelMapper;
        this.roleSearchFilterMapper = roleSearchFilterMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public PageDtoResponse<RoleDto> readAll(ResourceSearchFilterRequestDTO searchFilterRequest) {
        final ResourceSearchFilter searchFilter = roleSearchFilterMapper.map(searchFilterRequest);
        final Page<Role> page = repository.readAll(getEntitySearchSpecification(searchFilter));
        final List<RoleDto> modelDtoList = roleModelMapper.roleListToDtoList(page.entities());
        return new PageDtoResponse<>(modelDtoList, page.currentPage(), page.pageCount());
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto readById(Long id) {
        Optional<Role> roleModel = repository.readById(id);
        if (roleModel.isPresent()) {
            return roleModelMapper.modelToDto(roleModel.get());
        } else {
            throw new NotFoundException(
                    String.format(String.valueOf(ROLE_ID_DOES_NOT_EXIST.getMessage()), id));
        }
    }

    @Override
    @Transactional
    public RoleDto create(RoleDto createRequest) {
        try {
            Role model = roleModelMapper.dtoToModel(createRequest);
            model = repository.create(model);
            return roleModelMapper.modelToDto(model);
        } catch (EntityConflictRepositoryException ex) {
            throw new ResourceConflictServiceException(ROLE_CONFLICT.getMessage(), ROLE_CONFLICT.getErrorCode(), ex.getMessage());
        }
    }

    @Override
    @Transactional
    public RoleDto update(Long id, RoleDto updateRequest) {
        if (repository.existById(id)) {
            Role model = roleModelMapper.dtoToModel(updateRequest);
            model.setId(id);
            model = repository.update(model);
            return roleModelMapper.modelToDto(model);
        } else {
            throw new NotFoundException(String.format(ROLE_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (repository.existById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException(String.format(ROLE_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    public RoleDto findByName(String name) {
        return repository.findByName(name).map(roleModelMapper::modelToDto).orElseThrow(
                () -> new NotFoundException(String.format(ROLE_NAME_DOES_NOT_EXIST.getMessage(), name)));
    }
}
