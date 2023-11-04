package com.mjc.school.repository;

import com.mjc.school.repository.model.impl.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findUserModelByEmail(String email);
    Optional<UserModel> findUserModelByEmailOrUserName(String userName, String email);
    Optional<UserModel> findUserModelByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);



}
