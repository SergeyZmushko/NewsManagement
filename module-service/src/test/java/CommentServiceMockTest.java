import com.mjc.school.service.dto.*;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.implementation.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceMockTest {

    @Mock
    private CommentService commentService;

    @Test
    void findAllAuthorsTest(){
        ResourceSearchFilterRequestDTO resourceSearchFilterRequestDTO = mock(ResourceSearchFilterRequestDTO.class);
        PageDtoResponse modelPageDtoResponse = mock(PageDtoResponse.class);
        when(commentService.readAll(resourceSearchFilterRequestDTO)).thenReturn(modelPageDtoResponse);
        Assertions.assertEquals(commentService.readAll(resourceSearchFilterRequestDTO), modelPageDtoResponse);
    }

    @Test
    void createAuthorTest() {
        CommentDtoRequest commentDtoRequest = new CommentDtoRequest("name", 1L);
        CommentDtoResponse commentDtoResponse = mock(CommentDtoResponse.class);
        when(commentService.create(any(CommentDtoRequest.class))).thenReturn(commentDtoResponse);
        Assertions.assertEquals(commentService.create(commentDtoRequest), commentDtoResponse);
    }

    @Test
    void createAuthorThrowExceptionTest(){
        CommentDtoRequest commentDtoRequest = new CommentDtoRequest("Ni", 2L);
        when(commentService.create(commentDtoRequest)).thenThrow(ResourceConflictServiceException.class);
        Assertions.assertThrows(ResourceConflictServiceException.class, () -> commentService.create(commentDtoRequest));
    }

    @Test
    void readAuthorByIdTest(){
        CommentDtoResponse commentDtoResponse = mock(CommentDtoResponse.class);
        when(commentService.readById(1L)).thenReturn(commentDtoResponse);
        Assertions.assertEquals(commentDtoResponse, commentService.readById(1L));
    }

    @Test
    void updateAuthorTest(){
        CommentDtoRequest commentDtoRequest = mock(CommentDtoRequest.class);
        CommentDtoResponse commentDtoResponse = mock(CommentDtoResponse.class);
        when(commentService.update(1L, commentDtoRequest)).thenReturn(commentDtoResponse);
        Assertions.assertEquals(commentDtoResponse, commentService.update(1L, commentDtoRequest));
    }

    @Test
    void updateAuthorThrowExceptionTest(){
        CommentDtoRequest commentDtoRequest = new CommentDtoRequest("Nick", 1L);
        when(commentService.update(1L, commentDtoRequest)).thenThrow(NotFoundException.class);
        Assertions.assertThrows(NotFoundException.class, () -> commentService.update(1L, commentDtoRequest));
    }

    @Test
    void readByNewsIdTest(){
        List<CommentDtoResponse> commentDtoResponses = List.of(mock(CommentDtoResponse.class), mock(CommentDtoResponse.class));
        when(commentService.readByNewsId(anyLong())).thenReturn(commentDtoResponses);
        Assertions.assertEquals(commentDtoResponses, commentService.readByNewsId(2L));
    }

}
