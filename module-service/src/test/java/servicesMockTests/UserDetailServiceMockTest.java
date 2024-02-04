package servicesMockTests;

import com.mjc.school.interfaces.UserRepository;
import com.mjc.school.model.impl.Role;
import com.mjc.school.model.impl.UserModel;
import com.mjc.school.service.dto.SignUpDtoRequest;
import com.mjc.school.service.dto.UserDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.implementation.UserDetailsService;
import com.mjc.school.service.interfaces.UserModelMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceMockTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserModelMapper mapper;

    @InjectMocks
    private UserDetailsService userDetailsService;


    @Test
    void loadUserByUsernameTest() {
        String userNameOrEmail = "Legalas";
        UserModel userModel = new UserModel();
        userModel.setUserName("Legalas");
        userModel.setPassword("123456");

        Role admin = new Role();
        admin.setId(1L);
        admin.setName("Admin");

        Role user = new Role();
        user.setId(1L);
        user.setName("User");

        userModel.setRoles(Set.of(admin, user));

        when(repository.findUserModelByEmailOrUserName(userNameOrEmail, userNameOrEmail)).thenReturn(Optional.of(userModel));

        User userByUsername = (User) userDetailsService.loadUserByUsername(userNameOrEmail);

        Assertions.assertEquals("Legalas", userByUsername.getUsername());
    }

    @Test
    public void loadUserByUsernameThrowUsernameNotFoundExceptionTest() {
        String userNameOrEmail = "Legalas";
        when(repository.findUserModelByEmailOrUserName(userNameOrEmail, userNameOrEmail)).thenReturn(Optional.empty());
        Assertions.assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(userNameOrEmail));
    }

    @Test
    public void findUserModelByEmail() {
        String email = "legalas@gmail.com";
        UserModel userModel = new UserModel();
        userModel.setUserName("Legalas");
        userModel.setPassword("123456");
        userModel.setEmail(email);

        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setUserName(userModel.getUserName());

        when(repository.findUserModelByEmail(email)).thenReturn(Optional.of(userModel));
        when(mapper.modelToDto(userModel)).thenReturn(userDtoResponse);

        UserDtoResponse response = userDetailsService.findUserModelByEmail(email);

        Assertions.assertEquals("Legalas", response.getUserName());
    }

    @Test
    public void findUserModelByEmailThrowNotFoundException() {
        String email = "legalas@gmail.com";
        when(repository.findUserModelByEmail(email)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> userDetailsService.findUserModelByEmail(email));
    }

    @Test
    public void findUserModelByEmailOrUserNameTest() {
        String email = "legalas@gmail.com";
        String username = "Legalas";
        UserModel userModel = new UserModel();
        userModel.setUserName(username);
        userModel.setPassword("123456");
        userModel.setEmail(email);

        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setUserName(userModel.getUserName());

        when(repository.findUserModelByEmailOrUserName(username, email)).thenReturn(Optional.of(userModel));
        when(mapper.modelToDto(userModel)).thenReturn(userDtoResponse);

        UserDtoResponse response = userDetailsService.findUserModelByEmailOrUserName(username, email);

        Assertions.assertEquals("Legalas", response.getUserName());
    }

    @Test
    public void findUserModelByEmailOrUserNameThrowNotFoundExceptionTest() {
        String username = "Legalas";
        String email = "legalas@gmail.com";
        when(repository.findUserModelByEmailOrUserName(username, email)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> userDetailsService.findUserModelByEmailOrUserName(username, email));
    }

    @Test
    public void findUserModelByUserNameTest() {
        String username = "Legalas";
        UserModel userModel = new UserModel();
        userModel.setUserName(username);
        userModel.setPassword("123456");
        userModel.setEmail("legalas@gmail.com");

        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setUserName(userModel.getUserName());

        when(repository.findUserModelByUserName(username)).thenReturn(Optional.of(userModel));
        when(mapper.modelToDto(userModel)).thenReturn(userDtoResponse);

        UserDtoResponse response = userDetailsService.findUserModelByUserName(username);

        Assertions.assertEquals("Legalas", response.getUserName());
    }

    @Test
    public void findUserModelByUserNameThrowNotFoundExceptionTest() {
        String username = "Legalas";
        when(repository.findUserModelByUserName(username)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> userDetailsService.findUserModelByUserName(username));
    }

    @Test
    public void saveUserTest() {
        SignUpDtoRequest signUpDtoRequest = new SignUpDtoRequest();
        signUpDtoRequest.setName("Legalas");
        signUpDtoRequest.setEmail("legalas@gmail.com");
        signUpDtoRequest.setPassword("123456");

        UserModel userModel = new UserModel();
        userModel.setName(signUpDtoRequest.getName());
        userModel.setEmail(signUpDtoRequest.getEmail());
        userModel.setPassword(signUpDtoRequest.getPassword());

        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setUserName(userModel.getName());

        when(mapper.dtoToModel(signUpDtoRequest)).thenReturn(userModel);
        when(repository.save(userModel)).thenReturn(userModel);
        when(mapper.modelToDto(userModel)).thenReturn(userDtoResponse);

        UserDtoResponse response = userDetailsService.save(signUpDtoRequest);

        Assertions.assertEquals("Legalas", response.getUserName());
    }


}
