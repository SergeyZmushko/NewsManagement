import com.mjc.school.service.dto.*;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidatorException;
import com.mjc.school.service.implementation.NewsService;
import com.mjc.school.service.implementation.TagService;
import config.ConfigurationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(classes = ConfigurationTest.class)
public class TagServiceTest {
//
//    @Autowired
//    public TagService tagService;
//    @Autowired
//    public NewsService newsService;
//
//    @Test
//    void createTagTest() {
//        newsService.create(new CreateNewsDtoRequest("COMMERCE AND TRADE",
//                "A landlord's heartwarming Christmas present.",
//                "Ivan Petrov",
//                new ArrayList<>(),
//                new ArrayList<>()));
//        TagDtoResponse tagDtoResponse = tagService.create(new TagDtoRequest("Sport"));
//        Assertions.assertEquals(tagDtoResponse.name(), "Sport");
//    }
//
//    @Test
//    void createTagThrowValidatorException() {
//        Exception exception = assertThrows(ValidatorException.class,
//                () -> tagService.create(new TagDtoRequest("Mu")));
//        String expectedMessage = "Constraint 'Size' violated for the value 'Mu'";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    void readTagsByNewsIdTest() {
//        newsService.create(new CreateNewsDtoRequest("COMMERCE AND TRADE",
//                "A landlord's heartwarming Christmas present.",
//                "Ivan Petrov",
//                new ArrayList<>(),
//                new ArrayList<>()));
//        List<TagDtoResponse> tagDtoResponse = tagService.readByNewsId(1L);
//        assertEquals(0, tagDtoResponse.size());
//    }
//
//    @Test
//    void readTagByNewsIdTestWhenZeroTags() {
//        List<TagDtoResponse> tagDtoResponses = tagService.readByNewsId(999L);
//        assertEquals(tagDtoResponses.size(), 0);
//    }
//
//
//    @Test
//    void readAllTagsTest() {
//        tagService.create(new TagDtoRequest("Sport"));
//        PageDtoResponse<TagDtoResponse> tag = tagService.readAll(
//                new ResourceSearchFilterRequestDTO(1, 1, List.of("name:asc"), List.of("name:like:Sport")));
//
//        assertEquals("Sport", tag.modelDtoList().get(0).name());
//    }
//
//    @Test
//    void readTagByIdTest() {
//        TagDtoResponse tagDtoResponse = tagService.readById(2L);
//        assertFalse(tagDtoResponse.name().isEmpty());
//    }
//
//    @Test
//    void readTagByIdTestThrowNotFoundException() {
//        Exception exception = assertThrows(NotFoundException.class, () -> {
//            tagService.readById(999L);
//        });
//
//        String expectedMessage = "Tag Id does not exist. Tag Id is: 999";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    void updateTagTest() {
//        tagService.update(2L, new TagDtoRequest("Crime"));
//        assertEquals("Crime", tagService.readById(2L).name());
//    }
//
//    @Test
//    void updateTagTestThrowNotFoundException() {
//        Exception exception = assertThrows(NotFoundException.class, () -> {
//            tagService.update(999L, new TagDtoRequest("Finance"));
//        });
//
//        String expectedMessage = "Tag Id does not exist. Tag Id is: 999";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @Test
//    void deleteTagTest() {
//        newsService.create(new CreateNewsDtoRequest("COMMERCE AND TRADE",
//                "A landlord's heartwarming Christmas present.",
//                "Ivan Petrov",
//                new ArrayList<>(),
//                new ArrayList<>()));
//        tagService.create(new TagDtoRequest("Every year he sees that"));
//        TagDtoResponse tagDtoResponse = tagService.readById(1L);
//        tagService.deleteById(1L);
//        PageDtoResponse<TagDtoResponse> author = tagService.readAll(
//                new ResourceSearchFilterRequestDTO(1, 1, List.of("name:asc"), List.of("name:like:" + tagDtoResponse.name())));
//        assertEquals(author.modelDtoList().size(), 0);
//    }
//
//    @Test
//    void deleteTagTestThrowNotFoundException() {
//        Exception exception = assertThrows(NotFoundException.class, () -> tagService.deleteById(999L));
//
//        String expectedMessage = "Tag Id does not exist. Tag Id is: 999";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
}
