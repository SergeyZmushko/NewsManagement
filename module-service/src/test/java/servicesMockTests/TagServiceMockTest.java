package servicesMockTests;

import com.mjc.school.interfaces.TagRepository;
import com.mjc.school.model.impl.TagModel;
import com.mjc.school.service.dto.*;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.implementation.TagService;
import com.mjc.school.service.interfaces.TagModelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceMockTest {

    @Mock
    private TagRepository repository;

    @Mock
    private TagModelMapper mapper;

    @InjectMocks
    private TagService tagService;

    @Test
    void createTagTest() {
        TagDtoRequest tagDtoRequest = new TagDtoRequest("Music");
        TagModel tagModel = new TagModel();
        tagModel.setId(1L);
        tagModel.setName("Music");
        TagDtoResponse tagDtoResponse = new TagDtoResponse(1L, "Music");
        when(mapper.dtoToModel(tagDtoRequest)).thenReturn(tagModel);
        when(repository.save(tagModel)).thenReturn(tagModel);
        when(mapper.modelToDto(tagModel)).thenReturn(tagDtoResponse);

        TagDtoResponse response = tagService.create(tagDtoRequest);
        Assertions.assertEquals(response.id(), tagDtoResponse.id());
        Assertions.assertEquals(response.name(), tagDtoResponse.name());
    }

    @Test
    void readTagByIdTest() {
        TagModel tagModel = new TagModel();
        tagModel.setId(1L);
        tagModel.setName("name");

        TagDtoResponse tagDtoResponse = new TagDtoResponse(1L, "name");
        when(repository.findById(1L)).thenReturn(Optional.of(tagModel));
        when(mapper.modelToDto(tagModel)).thenReturn(tagDtoResponse);

        TagDtoResponse result = tagService.readById(1L);

        Assertions.assertEquals(result.id(), 1L);
        Assertions.assertEquals(result.name(), "name");
    }

    @Test
    void updateTagTest() {
        TagModel tagModel = new TagModel();
        tagModel.setId(1L);
        tagModel.setName("name");

        TagModel updatedTag = new TagModel();
        updatedTag.setId(1L);
        updatedTag.setName("New name");

        TagDtoRequest tagDtoRequest = new TagDtoRequest("New name");
        TagDtoResponse tagDtoResponse = new TagDtoResponse(updatedTag.getId(), updatedTag.getName());

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.of(tagModel));

        when(repository.save(tagModel)).thenReturn(updatedTag);
        when(mapper.modelToDto(updatedTag)).thenReturn(tagDtoResponse);

        TagDtoResponse result = tagService.update(1L, tagDtoRequest);

        Assertions.assertEquals(1L, result.id());
        Assertions.assertEquals(result.name(), "New name");
    }


    @Test
    void updateTagThrowNotFoundExceptionTest() {
        TagDtoRequest tagDtoRequest = new TagDtoRequest("Nick");
        when(repository.existsById(1L)).thenReturn(false);
        Assertions.assertThrows(NotFoundException.class, () -> tagService.update(1L, tagDtoRequest));
    }

    @Test
    void deleteTagTest() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(any());
        assertAll(() -> tagService.deleteById(1L));
    }

    @Test
    void readByNewsIdTest() {
        TagModel tagModel = new TagModel();
        tagModel.setName("Music");
        tagModel.setId(1L);
        List<TagModel> tagModelList = new ArrayList<>(List.of(tagModel));

        TagDtoResponse tagDtoResponse = new TagDtoResponse(1L, "Music");
        List<TagDtoResponse> tagDtoResponses = new ArrayList<>(List.of(tagDtoResponse));

        when(repository.findByNewsModelsId(1L)).thenReturn(tagModelList);
        when(mapper.modelListToDtoList(tagModelList)).thenReturn(tagDtoResponses);

        List<TagDtoResponse> response = tagService.readByNewsId(1L);

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals("Music", response.get(0).name());
    }

}
