import com.mjc.school.service.dto.*;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidatorException;
import com.mjc.school.service.implementation.AuthorService;
import com.mjc.school.service.implementation.NewsService;
import config.ConfigurationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(classes = ConfigurationTest.class)
public class AuthorServiceTest {
//
//    @Autowired
//    public AuthorService authorService;
//    @Autowired
//    public NewsService newsService;
//
//    @Test
//    void createAuthorTest() {
//        AuthorDtoResponse authorDtoResponse = authorService.create(new AuthorDtoRequest("Dmitry Pavlov"));
//        Assertions.assertEquals(authorDtoResponse.name(), "Dmitry Pavlov");
//    }
//
//    @Test
//    void createAuthorThrowValidatorException() {
//        Exception exception = assertThrows(ValidatorException.class, () -> {
//            authorService.create(new AuthorDtoRequest("Iv"));
//        });
//        String expectedMessage = "Constraint 'Size' violated for the value 'Iv'";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    void readAuthorByNewsIdTest() {
//        newsService.create(new CreateNewsDtoRequest("COMMERCE AND TRADE",
//                "A landlord's heartwarming Christmas present.",
//                "Ivan Petrov",
//                new ArrayList<>(),
//                new ArrayList<>()));
//        AuthorDtoResponse authorDtoResponse = authorService.readByNewsId(1L);
//        assertEquals("Ivan Petrov", authorDtoResponse.name());
//    }
//
//    @Test
//    void readAuthorByNewsIdTestThrowNotFoundException() {
//        Exception exception = assertThrows(NotFoundException.class, () -> {
//            authorService.readByNewsId(999L);
//        });
//        String expectedMessage = "Author not found for news with id 999.";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//
//    @Test
//    void readAllAuthorsTest() {
//        authorService.create(new AuthorDtoRequest("Gleb Smirnov"));
//        PageDtoResponse<AuthorDtoResponse> author = authorService.readAll(
//                new ResourceSearchFilterRequestDTO(1, 1, List.of("name:asc"), List.of("name:like:Gleb Smirnov")));
//
//        assertEquals("Gleb Smirnov", author.modelDtoList().get(0).name());
//    }
//
//    @Test
//    void readAuthorByIdTest() {
//        AuthorDtoResponse authorDtoResponse = authorService.readById(2L);
//        assertFalse(authorDtoResponse.name().isEmpty());
//    }
//
//    @Test
//    void readAuthorByIdTestThrowNotFoundException() {
//        Exception exception = assertThrows(NotFoundException.class, () -> {
//            authorService.readById(999L);
//        });
//
//        String expectedMessage = "Author Id does not exist. Author Id is: 999";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    void updateAuthorTest() {
//        authorService.update(2L, new AuthorDtoRequest("Irina Shilo"));
//        assertEquals("Irina Shilo", authorService.readById(2L).name());
//    }
//
//    @Test
//    void updateAuthorTestThrowNotFoundException() {
//        Exception exception = assertThrows(NotFoundException.class, () -> {
//            authorService.update(999L, new AuthorDtoRequest("Gleb Shirokov"));
//        });
//
//        String expectedMessage = "Author Id does not exist. Author Id is: 999";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    void deleteAuthorTest() {
//        AuthorDtoResponse authorDtoResponse = authorService.readById(1L);
//        authorService.deleteById(1L);
//        PageDtoResponse<AuthorDtoResponse> author = authorService.readAll(
//                new ResourceSearchFilterRequestDTO(1, 1, List.of("name:asc"), List.of("name:like:" + authorDtoResponse.name())));
//        assertEquals(author.modelDtoList().size(), 0);
//    }
//
//    @Test
//    void deleteAuthorTestThrowNotFoundException() {
//        Exception exception = assertThrows(NotFoundException.class, () -> {
//            authorService.deleteById(999L);
//        });
//
//        String expectedMessage = "Author Id does not exist. Author Id is: 999";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
}
