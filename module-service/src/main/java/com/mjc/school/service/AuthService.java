package com.mjc.school.service;

import com.mjc.school.service.dto.SignUpDtoRequest;
import com.mjc.school.service.dto.UserDtoResponse;
import com.mjc.school.service.dto.UserLoginDtoRequest;

public interface AuthService {

    UserDtoResponse save(SignUpDtoRequest userModel);

    UserDtoResponse login(UserLoginDtoRequest userLoginDtoRequest);

}
