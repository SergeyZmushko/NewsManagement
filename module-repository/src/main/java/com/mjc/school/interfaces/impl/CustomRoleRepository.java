package com.mjc.school.interfaces.impl;

import com.mjc.school.model.impl.Role;

import java.util.Optional;

public interface CustomRoleRepository {
    Optional<Role> findByName(String name);
}
