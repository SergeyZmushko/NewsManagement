package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.model.impl.Role;
import com.mjc.school.repository.interfaces.impl.CustomRoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, CustomRoleRepository, JpaSpecificationExecutor<Role> {

}
