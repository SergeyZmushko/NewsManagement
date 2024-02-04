package servicesMockTests;

import com.mjc.school.interfaces.CommentRepository;
import com.mjc.school.interfaces.NewsRepository;

import com.mjc.school.model.impl.Comment;
import com.mjc.school.model.impl.NewsModel;
import com.mjc.school.service.dto.*;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.implementation.CommentService;
import com.mjc.school.service.interfaces.CommentModelMapper;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceMockTest {

    @Mock
    private CommentRepository repository;

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private CommentModelMapper mapper;

    @InjectMocks
    private CommentService commentService;

    @Test
    void createCommentTest() {
        CommentDtoRequest commentDtoRequest = new CommentDtoRequest("Hello", 12L);
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("Hello");
        CommentDtoResponse commentDtoResponse = new CommentDtoResponse(1L, "Hello", 12L, LocalDateTime.now(), LocalDateTime.now());
        when(newsRepository.existsById(12L)).thenReturn(true);
        when(mapper.dtoToModel(commentDtoRequest)).thenReturn(comment);
        when(repository.save(comment)).thenReturn(comment);
        when(mapper.modelToDto(comment)).thenReturn(commentDtoResponse);

        CommentDtoResponse response = commentService.create(commentDtoRequest);
        Assertions.assertEquals(response.id(), commentDtoResponse.id());
        Assertions.assertEquals(response.content(), commentDtoResponse.content());
        Assertions.assertEquals(response.newsId(), commentDtoResponse.newsId());
    }

    @Test
    void createCommentTestThrowNotFoundException() {
        CommentDtoRequest commentDtoRequest = new CommentDtoRequest("Hello", 12L);
        when(newsRepository.existsById(12L)).thenReturn(false);
        Assertions.assertThrows(NotFoundException.class, () -> commentService.create(commentDtoRequest));
    }


    @Test
    void readCommentByIdTest() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("Hello");

        CommentDtoResponse commentDtoResponse = new CommentDtoResponse(1L, "Hello", 12L, LocalDateTime.now(), LocalDateTime.now());
        when(repository.findById(1L)).thenReturn(Optional.of(comment));
        when(mapper.modelToDto(comment)).thenReturn(commentDtoResponse);

        CommentDtoResponse result = commentService.readById(1L);

        Assertions.assertEquals(result.id(), 1L);
        Assertions.assertEquals(result.content(), "Hello");
    }

    @Test
    void readCommentByIdTestThrowNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> commentService.readById(1L));
    }

    @Test
    void updateCommentTest() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("Hello");

        Comment updatedComment = new Comment();
        updatedComment.setId(1L);
        updatedComment.setContent("New hello");

        CommentDtoRequest commentDtoRequest = new CommentDtoRequest("New name", 1L);
        CommentDtoResponse resultResponse = new CommentDtoResponse(1L, "New hello", 12L, LocalDateTime.now(), LocalDateTime.now());

        NewsModel news = new NewsModel();
        news.setTitle("ABCDE");
        news.setContent("This is the greatest achievement in our life");

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.of(comment));
        when(newsRepository.findById(commentDtoRequest.newsId())).thenReturn(Optional.of(news));

        when(repository.save(comment)).thenReturn(updatedComment);
        when(mapper.modelToDto(updatedComment)).thenReturn(resultResponse);

        CommentDtoResponse result = commentService.update(1L, commentDtoRequest);

        Assertions.assertEquals(1L, result.id());
        Assertions.assertEquals(result.content(), "New hello");
    }

    @Test
    void updateCommentThrowNotFoundExceptionTest() {
        CommentDtoRequest commentDtoRequest = new CommentDtoRequest("Nick", 12L);
        when(repository.existsById(1L)).thenReturn(false);
        Assertions.assertThrows(NotFoundException.class, () -> commentService.update(1L, commentDtoRequest));
    }

    @Test
    void deleteCommentTest() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(any());
        assertAll(() -> commentService.deleteById(1L));
    }

    @Test
    void readByNewsIdTest() {
        Comment comment = new Comment();
        comment.setContent("name");
        comment.setId(1L);
        List<Comment> commentsList = new ArrayList<>(List.of(comment));

        CommentDtoResponse commentDtoResponse = new CommentDtoResponse(1L, "New hello", 12L, LocalDateTime.now(), LocalDateTime.now());
        CommentDtoResponse commentDtoResponse1 = new CommentDtoResponse(2L, "New hello1", 12L, LocalDateTime.now(), LocalDateTime.now());

        List<CommentDtoResponse> responseList = new ArrayList<>(List.of(commentDtoResponse, commentDtoResponse1));
        when(repository.findByNewsModelId(1L)).thenReturn(commentsList);
        when(mapper.modelListToDtoList(commentsList)).thenReturn(responseList);

        List<CommentDtoResponse> response = commentService.readByNewsId(1L);

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals("New hello", response.get(0).content());
    }
}


