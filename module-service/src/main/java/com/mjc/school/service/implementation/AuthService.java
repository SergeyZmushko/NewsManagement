package com.mjc.school.service.implementation;

import com.mjc.school.service.dto.SignUpDtoRequest;
import com.mjc.school.service.dto.UserDtoResponse;
import com.mjc.school.service.dto.UserLoginDtoRequest;
import com.mjc.school.service.exceptions.EmailOrUserNameExistException;
import com.mjc.school.service.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AuthService implements com.mjc.school.service.AuthService {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder,
                       RoleService roleService, JwtUtils jwtUtils, AuthenticationManager authenticationManager){
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public UserDtoResponse save(SignUpDtoRequest userModel) {
        if(userDetailsService.existsByUserName(userModel.getUserName())){
            throw new EmailOrUserNameExistException("There is an account with that username: " + userModel.getUserName());
        }
        if(userDetailsService.existsByEmail(userModel.getEmail())){
            throw new EmailOrUserNameExistException("There is an account with that email: " + userModel.getEmail());
        }

        // create user object
        SignUpDtoRequest user = new SignUpDtoRequest();
        user.setName(userModel.getName());
        user.setUserName(userModel.getUserName());
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setRoles(Set.of(roleService.findByName("ROLE_USER")));
        return userDetailsService.save(user);
    }

    @Override
    public UserDtoResponse login(UserLoginDtoRequest userLoginDtoRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDtoRequest.getEmail(), userLoginDtoRequest.getPassword()));
        String token = jwtUtils.generateToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setUserName(userDetails.getUsername());
        userDtoResponse.setToken(token);
        return userDtoResponse;
    }
}
