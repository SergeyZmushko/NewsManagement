import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.PageDtoResponse;
import com.mjc.school.service.dto.ResourceSearchFilterRequestDTO;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.implementation.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthorServiceMockTest {

    @Mock
    private AuthorService authorService;

    @Test
    void findAllAuthorsTest(){
        ResourceSearchFilterRequestDTO resourceSearchFilterRequestDTO = mock(ResourceSearchFilterRequestDTO.class);
        PageDtoResponse modelPageDtoResponse = mock(PageDtoResponse.class);
        when(authorService.readAll(resourceSearchFilterRequestDTO)).thenReturn(modelPageDtoResponse);
        Assertions.assertEquals(authorService.readAll(resourceSearchFilterRequestDTO), modelPageDtoResponse);
    }

    @Test
    void createAuthorTest() {
        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest("name");
        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse(1L, "name");
        when(authorService.create(any(AuthorDtoRequest.class))).thenReturn(authorDtoResponse);
        Assertions.assertEquals(authorService.create(authorDtoRequest), authorDtoResponse);
    }

    @Test
    void createAuthorThrowExceptionTest(){
        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest("Ni");
        when(authorService.create(authorDtoRequest)).thenThrow(ResourceConflictServiceException.class);
        Assertions.assertThrows(ResourceConflictServiceException.class, () -> authorService.create(authorDtoRequest));
    }

    @Test
    void readAuthorByIdTest(){
        AuthorDtoResponse authorDtoResponse = mock(AuthorDtoResponse.class);
        when(authorService.readById(1L)).thenReturn(authorDtoResponse);
        Assertions.assertEquals(authorDtoResponse, authorService.readById(1L));
    }

    @Test
    void updateAuthorTest(){
        AuthorDtoRequest authorDtoRequest = mock(AuthorDtoRequest.class);
        AuthorDtoResponse authorDtoResponse = mock(AuthorDtoResponse.class);
        when(authorService.update(1L, authorDtoRequest)).thenReturn(authorDtoResponse);
        Assertions.assertEquals(authorDtoResponse, authorService.update(1L, authorDtoRequest));
    }

    @Test
    void updateAuthorThrowExceptionTest(){
        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest("Nick");
        when(authorService.update(1L, authorDtoRequest)).thenThrow(NotFoundException.class);
        Assertions.assertThrows(NotFoundException.class, () -> authorService.update(1L, authorDtoRequest));
    }

//    @Test
//    void deleteTest(){
//        doNothing().when(authorService).deleteById(any());
//
//    }

    @Test
    void readByNewsIdTest(){
        AuthorDtoResponse authorDtoResponse = mock(AuthorDtoResponse.class);
        when(authorService.readByNewsId(anyLong())).thenReturn(authorDtoResponse);
        Assertions.assertEquals(authorDtoResponse, authorService.readByNewsId(2L));
    }
}