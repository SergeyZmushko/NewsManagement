import com.mjc.school.service.dto.*;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.implementation.NewsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class NewsServiceMockTest {


    @Mock
    private NewsService newsService;

    @Test
    void findAllAuthorsTest(){
        ResourceSearchFilterRequestDTO resourceSearchFilterRequestDTO = mock(ResourceSearchFilterRequestDTO.class);
        PageDtoResponse modelPageDtoResponse = mock(PageDtoResponse.class);
        when(newsService.readAll(resourceSearchFilterRequestDTO)).thenReturn(modelPageDtoResponse);
        Assertions.assertEquals(newsService.readAll(resourceSearchFilterRequestDTO), modelPageDtoResponse);
    }

    @Test
    void createAuthorTest() {
        CreateNewsDtoRequest newsDtoRequest = mock(CreateNewsDtoRequest.class);
        NewsDtoResponse newsDtoResponse = mock(NewsDtoResponse.class);
        when(newsService.create(any(CreateNewsDtoRequest.class))).thenReturn(newsDtoResponse);
        Assertions.assertEquals(newsService.create(newsDtoRequest), newsDtoResponse);
    }

    @Test
    void createAuthorThrowExceptionTest(){
        CreateNewsDtoRequest createNewsDtoRequest = mock(CreateNewsDtoRequest.class);
        when(newsService.create(createNewsDtoRequest)).thenThrow(ResourceConflictServiceException.class);
        Assertions.assertThrows(ResourceConflictServiceException.class, () -> newsService.create(createNewsDtoRequest));
    }

    @Test
    void readAuthorByIdTest(){
        NewsDtoResponse newsDtoResponse = mock(NewsDtoResponse.class);
        when(newsService.readById(1L)).thenReturn(newsDtoResponse);
        Assertions.assertEquals(newsDtoResponse, newsService.readById(1L));
    }

    @Test
    void updateAuthorTest(){
        UpdateNewsDtoRequest updateNewsDtoRequest = mock(UpdateNewsDtoRequest.class);
        NewsDtoResponse newsDtoResponse = mock(NewsDtoResponse.class);
        when(newsService.update(1L, updateNewsDtoRequest)).thenReturn(newsDtoResponse);
        Assertions.assertEquals(newsDtoResponse, newsService.update(1L, updateNewsDtoRequest));
    }

    @Test
    void updateAuthorThrowExceptionTest(){
        UpdateNewsDtoRequest updateNewsDtoRequest = mock(UpdateNewsDtoRequest.class);
        when(newsService.update(1L, updateNewsDtoRequest)).thenThrow(NotFoundException.class);
        Assertions.assertThrows(NotFoundException.class, () -> newsService.update(1L, updateNewsDtoRequest));
    }

}