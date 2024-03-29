package com.mjc.school.service.implementation;

import com.mjc.school.exception.EntityConflictRepositoryException;
import com.mjc.school.model.impl.Role;
import com.mjc.school.interfaces.RoleRepository;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.PageDtoResponse;
import com.mjc.school.service.dto.ResourceSearchFilterRequestDTO;
import com.mjc.school.service.dto.RoleDto;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.interfaces.RoleModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

@Service
public class RoleService implements BaseService<RoleDto, RoleDto, Long, Role, RoleDto> {

    private final RoleRepository repository;
    private final RoleModelMapper roleModelMapper;

    @Autowired
    public RoleService(RoleRepository repository, RoleModelMapper roleModelMapper){
        this.repository = repository;
        this.roleModelMapper = roleModelMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public PageDtoResponse<RoleDto> readAll(ResourceSearchFilterRequestDTO searchFilterRequestDTO, Pageable pageable) {
        Page<Role> page;
        if (searchFilterRequestDTO != null) {
            page = repository.findAll(getSpecification(searchFilterRequestDTO), pageable);

        } else {
            page = repository.findAll(pageable);
        }
        Page<RoleDto> result = roleModelMapper.rolePageToDtoPage(page);
        return new PageDtoResponse<>(result.getContent(), result.getPageable().getPageNumber(), result.getTotalPages());
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto readById(Long id) {
        Optional<Role> roleModel = repository.findById(id);
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
            model = repository.save(model);
            return roleModelMapper.modelToDto(model);
        } catch (EntityConflictRepositoryException ex) {
            throw new ResourceConflictServiceException(ROLE_CONFLICT.getMessage(), ROLE_CONFLICT.getErrorCode(), ex.getMessage());
        }
    }

    @Override
    @Transactional
    public RoleDto update(Long id, RoleDto updateRequest) {
        if (repository.existsById(id)) {
            Role model = repository.findById(id).get();
            model.setName(updateRequest.name());
            model = repository.save(model);
            return roleModelMapper.modelToDto(model);
        } else {
            throw new NotFoundException(String.format(ROLE_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (repository.existsById(id)) {
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
