package servicesMockTests;

import com.mjc.school.repository.interfaces.AuthorRepository;
import com.mjc.school.repository.interfaces.CommentRepository;
import com.mjc.school.repository.interfaces.NewsRepository;
import com.mjc.school.repository.interfaces.TagRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.dto.*;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.implementation.NewsService;
import com.mjc.school.service.interfaces.NewsModelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsServiceMockTest {

    @Mock
    private NewsRepository repository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private NewsModelMapper mapper;
    @InjectMocks
    private NewsService newsService;

    @Test
    void createNewsTest() {
        CreateNewsDtoRequest newsDtoRequest = new CreateNewsDtoRequest("ABCDE", "Follow the instructions", "Adam Novel", new ArrayList<>(), new ArrayList<>());
        NewsModel newsModel = new NewsModel();
        newsModel.setId(1L);
        newsModel.setContent(newsDtoRequest.content());
        newsModel.setTitle(newsDtoRequest.title());

        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse(2L, "Adam Novel");
        TagDtoResponse tagDtoResponse = new TagDtoResponse(3L, "Sport");
        CommentsDtoForNewsResponse commentDtoResponse = new CommentsDtoForNewsResponse(5L, "It's amazing", LocalDateTime.now(), LocalDateTime.now());

        NewsDtoResponse newsDtoResponse = new NewsDtoResponse(1L, newsModel.getTitle(),
                newsModel.getContent(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                authorDtoResponse,
                List.of(tagDtoResponse),
                List.of(commentDtoResponse));
        when(mapper.dtoToModel(newsDtoRequest)).thenReturn(newsModel);
        when(repository.save(newsModel)).thenReturn(newsModel);
        when(mapper.modelToDto(newsModel)).thenReturn(newsDtoResponse);

        NewsDtoResponse response = newsService.create(newsDtoRequest);
        Assertions.assertEquals(1L, response.getId());
        Assertions.assertEquals("ABCDE", response.getTitle());
        Assertions.assertEquals("Follow the instructions", response.getContent());
    }

    @Test
    void readNewsByIdTest() {
        NewsModel newsModel = new NewsModel();
        newsModel.setId(1L);
        newsModel.setTitle("ABCDE");
        newsModel.setContent("Follow the instructions");

        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse(2L, "Adam Novel");
        TagDtoResponse tagDtoResponse = new TagDtoResponse(3L, "Sport");
        CommentsDtoForNewsResponse commentDtoResponse = new CommentsDtoForNewsResponse(5L, "It's amazing", LocalDateTime.now(), LocalDateTime.now());

        NewsDtoResponse newsDtoResponse = new NewsDtoResponse(
                1L,
                newsModel.getTitle(),
                newsModel.getContent(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                authorDtoResponse,
                List.of(tagDtoResponse),
                List.of(commentDtoResponse));
        when(repository.findById(1L)).thenReturn(Optional.of(newsModel));
        when(mapper.modelToDto(newsModel)).thenReturn(newsDtoResponse);

        NewsDtoResponse result = newsService.readById(1L);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("ABCDE", result.getTitle());
        Assertions.assertEquals("Follow the instructions", result.getContent());
    }

    @Test
    void readNewsByIdThrowNotFoundExceptionTest() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> newsService.readById(1L));
    }

    @Test
    void updateNewsTest() {
        UpdateNewsDtoRequest updateNewsDtoRequest = new UpdateNewsDtoRequest("ABCDE", "Follow the instructions", "Adam Novel", new ArrayList<>(), new ArrayList<>());
        NewsModel newsModel = new NewsModel();
        newsModel.setId(1L);
        newsModel.setTitle("name");

        NewsModel updatedNews = new NewsModel();
        updatedNews.setId(1L);
        updatedNews.setTitle("New name");

        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse(2L, "Adam Novel");
        TagDtoResponse tagDtoResponse = new TagDtoResponse(3L, "Sport");
        CommentsDtoForNewsResponse commentDtoResponse = new CommentsDtoForNewsResponse(5L, "It's amazing", LocalDateTime.now(), LocalDateTime.now());

        NewsDtoResponse resultResponse = new NewsDtoResponse(
                1L,
                updatedNews.getTitle(),
                updatedNews.getContent(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                authorDtoResponse,
                List.of(tagDtoResponse),
                List.of(commentDtoResponse));

        when(repository.findById(1L)).thenReturn(Optional.of(newsModel));

        when(repository.save(newsModel)).thenReturn(updatedNews);
        when(mapper.modelToDto(updatedNews)).thenReturn(resultResponse);

        NewsDtoResponse result = newsService.update(1L, updateNewsDtoRequest);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("New name", result.getTitle());
    }


    @Test
    void updateNewsThrowNotFoundExceptionTest() {
        UpdateNewsDtoRequest updateNewsDtoRequest = new UpdateNewsDtoRequest("ABCDE", "Follow the instructions", "Adam Novel", new ArrayList<>(), new ArrayList<>());
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> newsService.update(1L, updateNewsDtoRequest));
    }

    @Test
    void deleteTest() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(any());
        assertAll(() -> newsService.deleteById(1L));
    }

    @Test
    void deleteThrowNotFoundExceptionTest() {
        when(repository.existsById(1L)).thenReturn(false);
        Assertions.assertThrows(NotFoundException.class, () -> newsService.deleteById(1L));
    }


}