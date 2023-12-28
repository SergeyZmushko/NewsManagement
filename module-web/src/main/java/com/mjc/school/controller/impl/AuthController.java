package com.mjc.school.controller.impl;

import com.mjc.school.service.dto.SignUpDtoRequest;
import com.mjc.school.service.dto.UserDtoResponse;
import com.mjc.school.service.dto.UserLoginDtoRequest;
import com.mjc.school.service.implementation.AuthService;
import com.mjc.school.versioning.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@ApiVersion
@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDtoResponse> authenticateUser(@RequestBody UserLoginDtoRequest loginDto){
        UserDtoResponse response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDtoRequest signUpDto){
        authService.save(signUpDto);
        return new ResponseEntity<>("UserModel registered successfully", HttpStatus.OK);
    }

}
