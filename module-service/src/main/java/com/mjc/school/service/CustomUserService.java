package com.mjc.school.service;

import com.mjc.school.service.dto.SignUpDtoRequest;
import com.mjc.school.service.dto.UserDtoResponse;
import org.springframework.stereotype.Service;


@Service
public interface CustomUserService {
    UserDtoResponse findUserModelByEmail(String email);
    UserDtoResponse findUserModelByEmailOrUserName(String userName, String email);
    UserDtoResponse findUserModelByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);

    UserDtoResponse save(SignUpDtoRequest userModel);

}
