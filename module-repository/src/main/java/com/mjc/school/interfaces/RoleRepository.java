package com.mjc.school.interfaces;

import com.mjc.school.interfaces.impl.CustomRoleRepository;
import com.mjc.school.model.impl.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, CustomRoleRepository, JpaSpecificationExecutor<Role> {

}
