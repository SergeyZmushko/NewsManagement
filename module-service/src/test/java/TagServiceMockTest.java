import com.mjc.school.service.dto.*;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.implementation.TagService;
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
class TagServiceMockTest {

    @Mock
    private TagService tagService;

    @Test
    void findAllAuthorsTest(){
//        ResourceSearchFilterRequestDTO resourceSearchFilterRequestDTO = mock(ResourceSearchFilterRequestDTO.class);
//        PageDtoResponse modelPageDtoResponse = mock(PageDtoResponse.class);
//        when(tagService.readAll(resourceSearchFilterRequestDTO)).thenReturn(modelPageDtoResponse);
//        Assertions.assertEquals(tagService.readAll(resourceSearchFilterRequestDTO), modelPageDtoResponse);
    }

    @Test
    void createAuthorTest() {
        TagDtoRequest tagDtoRequest = new TagDtoRequest("name");
        TagDtoResponse tagDtoResponse = new TagDtoResponse(1L, "name");
        when(tagService.create(any(TagDtoRequest.class))).thenReturn(tagDtoResponse);
        Assertions.assertEquals(tagService.create(tagDtoRequest), tagDtoResponse);
    }

    @Test
    void createAuthorThrowExceptionTest(){
        TagDtoRequest tagDtoRequest = new TagDtoRequest("Ni");
        when(tagService.create(tagDtoRequest)).thenThrow(ResourceConflictServiceException.class);
        Assertions.assertThrows(ResourceConflictServiceException.class, () -> tagService.create(tagDtoRequest));
    }

    @Test
    void readAuthorByIdTest(){
        TagDtoResponse tagDtoResponse = mock(TagDtoResponse.class);
        when(tagService.readById(1L)).thenReturn(tagDtoResponse);
        Assertions.assertEquals(tagDtoResponse, tagService.readById(1L));
    }

    @Test
    void updateAuthorTest(){
        TagDtoRequest tagDtoRequest = mock(TagDtoRequest.class);
        TagDtoResponse tagDtoResponse = mock(TagDtoResponse.class);
        when(tagService.update(1L, tagDtoRequest)).thenReturn(tagDtoResponse);
        Assertions.assertEquals(tagDtoResponse, tagService.update(1L, tagDtoRequest));
    }

    @Test
    void updateAuthorThrowExceptionTest(){
        TagDtoRequest tagDtoRequest = new TagDtoRequest("Nick");
        when(tagService.update(1L, tagDtoRequest)).thenThrow(NotFoundException.class);
        Assertions.assertThrows(NotFoundException.class, () -> tagService.update(1L, tagDtoRequest));
    }

    @Test
    void readByNewsIdTest(){
        List<TagDtoResponse> tagDtoResponses = List.of(mock(TagDtoResponse.class), mock(TagDtoResponse.class));
        when(tagService.readByNewsId(anyLong())).thenReturn(tagDtoResponses);
        Assertions.assertEquals(tagDtoResponses, tagService.readByNewsId(2L));
    }

}
