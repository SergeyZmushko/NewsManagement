package serviceTest;

import com.mjc.school.repository.exception.EntityConflictRepositoryException;
import com.mjc.school.repository.implementation.TagRepository;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.implementation.TagService;
import com.mjc.school.service.interfaces.TagModelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceUnitTest {

    @Mock
    private TagRepository tagRepository;
    @Mock
    private TagModelMapper mapper;

    @InjectMocks
    private TagService tagService;

    private TagModel tagModel;
    private TagDtoRequest tagDtoRequest;
    private TagDtoResponse tagDtoResponse;


    @BeforeEach
    void setup(){
        tagModel = new TagModel();
        tagDtoRequest = new TagDtoRequest("Sport");
        tagDtoResponse = new TagDtoResponse(2L, "Sport");
    }

//    @Test
//    public void givenPageDto_whenReadAll_thenReturnPageDtoResponse(){
//        ResourceSearchFilter resourceSearchFilter = mock(ResourceSearchFilter.class);
//        ResourceSearchFilterRequestDTO resourceSearchFilterRequestDTO = mock(ResourceSearchFilterRequestDTO.class);
//        PageDtoResponse modelPageDtoResponse = mock(PageDtoResponse.class);
//        when(tagService.readAll(resourceSearchFilterRequestDTO)).thenReturn(modelPageDtoResponse);
//        Assertions.assertEquals(tagService.readAll(resourceSearchFilterRequestDTO), modelPageDtoResponse);
//    }

    @Test
    void givenTagDto_whenReadById_thenReturnTagDtoResponse(){
        given(tagRepository.readById(tagDtoResponse.id())).willReturn(Optional.of(tagModel));
        given(mapper.modelToDto(tagModel)).willReturn(tagDtoResponse);

        TagDtoResponse tagDtoResponse1 = tagService.readById(tagDtoResponse.id());
        assertThat(tagDtoResponse1.name()).isEqualTo("Sport");
    }

    @Test
    void givenTagDto_whenReadById_thenReturnNotFoundException(){
        given(tagRepository.readById(999L)).willThrow(NotFoundException.class);

        Assertions.assertThrows(NotFoundException.class, () -> tagService.readById(999L));
    }

    @Test
    void givenTag_whenCreate_thenReturnTagDtoResponse(){
        given(tagRepository.create(tagModel)).willReturn(tagModel);
        given(mapper.dtoToModel(tagDtoRequest)).willReturn(tagModel);
        given(mapper.modelToDto(tagModel)).willReturn(tagDtoResponse);

        TagDtoResponse tagDtoResponse1 = tagService.create(tagDtoRequest);

        assertThat(tagDtoResponse1.name()).isEqualTo("Sport");
        assertThat(tagDtoResponse1.id()).isEqualTo(2L);
    }

    @Test
    void givenTagDtoRequest_whenCreate_thenThrowsEntityConflictRepositoryException(){
        given(tagRepository.create(tagModel)).willThrow(EntityConflictRepositoryException.class);
        given(mapper.dtoToModel(tagDtoRequest)).willReturn(tagModel);
        Assertions.assertThrows(ResourceConflictServiceException.class, () -> tagService.create(tagDtoRequest));
//        verify(tagRepository, never()).create(any(tagModel.class));
    }

    @Test
    void givenTagDtoRequest_whenUpdate_thenReturnTagDtoResponse(){
        given(tagRepository.update(tagModel)).willReturn(tagModel);
        given(mapper.modelToDto(tagModel)).willReturn(tagDtoResponse);
        given(mapper.dtoToModel(tagDtoRequest)).willReturn(tagModel);
        given(tagRepository.existById(2L)).willReturn(true);

        TagDtoResponse tagDtoResponse1 = tagService.update(2L, tagDtoRequest);
        assertThat(tagDtoResponse1.name()).isEqualTo("Sport");
        assertThat(tagDtoResponse1.id()).isEqualTo(2L);
    }

    @Test
    void given_whenUpdate_thenThrowsNotFoundException(){
        given(tagRepository.existById(2L)).willReturn(false);

        Assertions.assertThrows(NotFoundException.class, () -> tagService.update(2L, tagDtoRequest));
    }

    @Test
    void givenTagDtoRequest_whenDelete_thenReturnNothing(){
        long tagId = 1L;
        given(tagRepository.existById(tagId)).willReturn(true);
        willDoNothing().given(tagRepository).deleteById(tagId);
        tagService.deleteById(tagId);

        verify(tagRepository, times(1)).deleteById(tagId);
    }

//    @Test
//    public void givenTagDtoRequest_whenDelete_thenThrowsNotFoundException(){
//        long tagId = 1L;
//        given(tagRepository.existById(tagId)).willReturn(false);
//
//        Assertions.assertThrows(NotFoundException.class, () -> tagService.deleteById(tagId));
//    }
//
//    @Test
//    public void givenTag_whenReadByNewsId_thenReturnTagDtoResponse(){
//        given(tagRepository.readByNewsId(1L)).willReturn(Optional.of(tagModel));
//        given(mapper.modelToDto(tagModel)).willReturn(tagDtoResponse);
//
//        TagDtoResponse tagDtoResponse = tagService.readByNewsId(1L);
//        assertThat(tagDtoResponse.name()).isEqualTo("Sport");
//        assertThat(tagDtoResponse).isNotNull();
//    }

//    @Test
//    public void giventagDtoRequest_whenReadByNewsId_thenThrowsNotFoundException(){
//        long tagId = 1L;
//        given(tagRepository.readByNewsId(tagId)).willReturn(Optional.empty());
//
//        Assertions.assertThrows(NotFoundException.class, () -> tagService.readByNewsId(tagId));
//    }

    @Test
    void givenTagDtoRequest_whenDelete_thenThrowsNotFoundException(){
        long tagId = 1L;
        given(tagRepository.existById(tagId)).willReturn(false);

        Assertions.assertThrows(NotFoundException.class, () -> tagService.deleteById(tagId));
    }

    @Test
    void givenComment_whenReadByNewsId_thenReturnCommentDtoResponse(){
        List<TagModel> tags = List.of(tagModel, new TagModel());
        given(tagRepository.readByNewsId(1L)).willReturn(tags);
        given(mapper.modelListToDtoList(tags)).willReturn(List.of(tagDtoResponse, tagDtoResponse));


        List<TagDtoResponse> tagDtoResponseList = tagService.readByNewsId(1L);
        assertThat(tagDtoResponseList.size()).isEqualTo(2);
        assertThat(tagDtoResponse).isNotNull();
    }

}
