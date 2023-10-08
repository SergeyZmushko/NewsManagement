import com.mjc.school.service.dto.*;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidatorException;
import com.mjc.school.service.implementation.CommentService;
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
public class CommentServiceTest {
//
//    @Autowired
//    public CommentService commentService;
//    @Autowired
//    public NewsService newsService;
//
//    @Test
//    void createCommentTest() {
//        newsService.create(new CreateNewsDtoRequest("COMMERCE AND TRADE",
//                "A landlord's heartwarming Christmas present.",
//                "Ivan Petrov",
//                new ArrayList<>(),
//                new ArrayList<>()));
//        CommentDtoResponse commentDtoResponse = commentService.create(new CommentDtoRequest("Hahaha, It's cool", 1L));
//        Assertions.assertEquals(commentDtoResponse.content(), "Hahaha, It's cool");
//    }
//
//    @Test
//    void createCommentThrowValidatorException() {
//        Exception exception = assertThrows(ValidatorException.class,
//                () -> commentService.create(new CommentDtoRequest("Ha", 1L)));
//        String expectedMessage = "Constraint 'Size' violated for the value 'Ha'";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    void createCommentThrowNotFoundException() {
//        Exception exception = assertThrows(NotFoundException.class,
//                () -> commentService.create(new CommentDtoRequest("HaHaha", 200L)));
//        System.out.println(exception.getMessage());
//        String expectedMessage = "News with id 200 does not exist.";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//
//    @Test
//    void readCommentsByNewsIdTest() {
//        newsService.create(new CreateNewsDtoRequest("COMMERCE AND TRADE",
//                "A landlord's heartwarming Christmas present.",
//                "Ivan Petrov",
//                new ArrayList<>(),
//                new ArrayList<>()));
//        List<CommentDtoResponse> commentDtoResponse = commentService.readByNewsId(1L);
//        assertEquals(2, commentDtoResponse.size());
//    }
//
//    @Test
//    void readCommentByNewsIdTestWhenZeroComments() {
//        List<CommentDtoResponse> commentDtoResponses = commentService.readByNewsId(999L);
//        assertEquals(commentDtoResponses.size(), 0);
//    }
//
//
//    @Test
//    void readAllCommentsTest() {
//        commentService.create(new CommentDtoRequest("Every year I see that", 1L));
//        PageDtoResponse<CommentDtoResponse> comment = commentService.readAll(
//                new ResourceSearchFilterRequestDTO(1, 1, List.of("content:asc"), List.of("content:like:Every year I see that")));
//
//        assertEquals("Every year I see that", comment.modelDtoList().get(0).content());
//    }
//
//    @Test
//    void readCommentByIdTest() {
//        CommentDtoResponse commentDtoResponse = commentService.readById(2L);
//        assertFalse(commentDtoResponse.content().isEmpty());
//    }
//
//    @Test
//    void readCommentByIdTestThrowNotFoundException() {
//        Exception exception = assertThrows(NotFoundException.class, () -> {
//            commentService.readById(999L);
//        });
//
//        String expectedMessage = "Comment with id 999 does not exist.";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    void updateCommentTest() {
//        commentService.update(2L, new CommentDtoRequest("Irina Shilo", 2L));
//        assertEquals("Irina Shilo", commentService.readById(2L).content());
//    }
//
//    @Test
//    void updateCommentTestThrowNotFoundException() {
//        Exception exception = assertThrows(NotFoundException.class, () -> {
//            commentService.update(999L, new CommentDtoRequest("Have a nice day", 999L));
//        });
//
//        String expectedMessage = "Comment with id 999 does not exist.";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    void deleteCommentTest() {
//        newsService.create(new CreateNewsDtoRequest("COMMERCE AND TRADE",
//                "A landlord's heartwarming Christmas present.",
//                "Ivan Petrov",
//                new ArrayList<>(),
//                new ArrayList<>()));
//        commentService.create(new CommentDtoRequest("Every year he sees that", 1L));
//        CommentDtoResponse commentDtoResponse = commentService.readById(1L);
//        commentService.deleteById(1L);
//        PageDtoResponse<CommentDtoResponse> author = commentService.readAll(
//                new ResourceSearchFilterRequestDTO(1, 1, List.of("content:asc"), List.of("content:like:" + commentDtoResponse.content())));
//        assertEquals(author.modelDtoList().size(), 0);
//    }
//
//    @Test
//    void deleteCommentTestThrowNotFoundException() {
//        Exception exception = assertThrows(NotFoundException.class, () -> commentService.deleteById(999L));
//
//        String expectedMessage = "Comment with id 999 does not exist";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
}
