package com.mjc.school.repository.interfaces.impl;

import com.mjc.school.repository.model.impl.Role;

import java.util.Optional;

public interface CustomRoleRepository {
    Optional<Role> findByName(String name);
}
