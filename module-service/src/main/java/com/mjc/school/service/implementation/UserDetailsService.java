package com.mjc.school.service.implementation;

import com.mjc.school.repository.interfaces.UserRepository;
import com.mjc.school.repository.model.impl.UserModel;
import com.mjc.school.service.CustomUserService;
import com.mjc.school.service.dto.SignUpDtoRequest;
import com.mjc.school.service.dto.UserDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.interfaces.UserModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;
import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService, CustomUserService {
    private final UserRepository userRepository;
    private final UserModelMapper mapper;


    @Autowired
    public UserDetailsService(UserRepository userRepository, UserModelMapper mapper){
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findUserModelByEmailOrUserName(userNameOrEmail, userNameOrEmail)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format(USER_DOES_NOT_EXIST_WITH_USERNAME_OR_EMAIL.getMessage(), userNameOrEmail, userNameOrEmail)));
        Set<GrantedAuthority> authorities = userModel
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new User(userModel.getUserName(), userModel.getPassword(), authorities);
    }

    @Override
    public UserDtoResponse findUserModelByEmail(String email) {
        return mapper.modelToDto(userRepository
                .findUserModelByEmail(email)
                .orElseThrow(() ->
                        new NotFoundException(String.format(USER_WITH_EMAIL_DOES_NOT_EXIST.getMessage(), email))));
    }

    @Override
    public UserDtoResponse findUserModelByEmailOrUserName(String userName, String email) {
        return mapper.modelToDto(userRepository
                .findUserModelByEmailOrUserName(userName, email)
                .orElseThrow(() ->
                        new NotFoundException(String.format(USER_DOES_NOT_EXIST_WITH_USERNAME_OR_EMAIL.getMessage(), userName, email))));
    }

    @Override
    public UserDtoResponse findUserModelByUserName(String userName) {
        return mapper.modelToDto(userRepository
                .findUserModelByUserName(userName)
                .orElseThrow(() ->
                        new NotFoundException(String.format(USER_WITH_USERNAME_DOES_NOT_EXIST.getMessage(), userName))));
    }

    @Override
    public Boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDtoResponse save(SignUpDtoRequest userModel) {
        return mapper.modelToDto(userRepository.save(mapper.dtoToModel(userModel)));
    }
}
