package serviceTest;

import com.mjc.school.repository.exception.EntityConflictRepositoryException;
import com.mjc.school.repository.implementation.AuthorRepository;
import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.implementation.TagRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.CreateNewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.UpdateNewsDtoRequest;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.implementation.NewsService;
import com.mjc.school.service.interfaces.NewsModelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsServiceUnitTest {

    @Mock
    private NewsRepository newsRepository;
    @Mock
    private NewsModelMapper mapper;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private TagRepository tagRepository;
    @InjectMocks
    private NewsService newsService;

    private NewsModel newsModel;
    private CreateNewsDtoRequest newsDtoRequest;
    private NewsDtoResponse newsDtoResponse;
    private AuthorDtoResponse authorDtoResponse;

    @BeforeEach
    void setup(){
        authorRepository = new AuthorRepository();
        tagRepository = new TagRepository();
        newsModel = new NewsModel();
        newsDtoRequest = new CreateNewsDtoRequest(
                "COMMERCE AND TRADE",
                "A landlord's heartwarming Christmas present.",
                "John Lennon",
                new ArrayList<>(),
                new ArrayList<>());
        authorDtoResponse = new AuthorDtoResponse(2L, "John Lennon");
        newsDtoResponse = new NewsDtoResponse(2L,
                "COMMERCE AND TRADE",
                "A landlord's heartwarming Christmas present.",
                LocalDateTime.now(),
                LocalDateTime.now(),
                authorDtoResponse,
                new ArrayList<>(),
                new ArrayList<>());
    }

//    @Test
//    public void givenPageDto_whenReadAll_thenReturnPageDtoResponse(){
//        ResourceSearchFilter resourceSearchFilter = mock(ResourceSearchFilter.class);
//        ResourceSearchFilterRequestDTO resourceSearchFilterRequestDTO = mock(ResourceSearchFilterRequestDTO.class);
//        PageDtoResponse modelPageDtoResponse = mock(PageDtoResponse.class);
//        when(newsService.readAll(resourceSearchFilterRequestDTO)).thenReturn(modelPageDtoResponse);
//        Assertions.assertEquals(newsService.readAll(resourceSearchFilterRequestDTO), modelPageDtoResponse);
//    }

    @Test
    void givenNewsDto_whenReadById_thenReturnNewsDtoResponse(){
        given(newsRepository.readById(newsDtoResponse.getId())).willReturn(Optional.of(newsModel));
        given(mapper.modelToDto(newsModel)).willReturn(newsDtoResponse);

        NewsDtoResponse newsDtoResponse1 = newsService.readById(newsDtoResponse.getId());
        assertThat(newsDtoResponse1.getTitle()).isEqualTo("COMMERCE AND TRADE");
        assertThat(newsDtoResponse1.getContent()).isEqualTo("A landlord's heartwarming Christmas present.");
        assertThat(newsDtoResponse1.getAuthorDto().name()).isEqualTo("John Lennon");
    }

    @Test
    void givenNewsDto_whenReadById_thenReturnNotFoundException(){
        given(newsRepository.readById(999L)).willThrow(NotFoundException.class);

        Assertions.assertThrows(NotFoundException.class, () -> newsService.readById(999L));
    }

    @Test
    void givenNews_whenCreate_thenReturnNewsDtoResponse(){
        given(newsRepository.create(newsModel)).willReturn(newsModel);
        given(mapper.dtoToModel(newsDtoRequest)).willReturn(newsModel);
        given(mapper.modelToDto(newsModel)).willReturn(newsDtoResponse);

        NewsDtoResponse newsDtoResponse1 = newsService.create(newsDtoRequest);

        assertThat(newsDtoResponse1.getTitle()).isEqualTo("COMMERCE AND TRADE");
        assertThat(newsDtoResponse1.getId()).isEqualTo(2L);
        assertThat(newsDtoResponse1.getContent()).isEqualTo("A landlord's heartwarming Christmas present.");
    }

    @Test
    void givenNewsDtoRequest_whenCreate_thenThrowsEntityConflictRepositoryException(){
//        AuthorModel authorModel = new AuthorModel();
//        given(authorRepository.readByName("John Lennon")).willReturn(Optional.of(authorModel));
//        given(authorRepository.create(authorModel)).willReturn(authorModel);
//        given(newsRepository.create(newsModel)).willThrow(EntityConflictRepositoryException.class);
//        given(mapper.dtoToModel(newsDtoRequest)).willReturn(newsModel);
//        Assertions.assertThrows(ResourceConflictServiceException.class, () -> newsService.create(newsDtoRequest));
//        verify(newsRepository, never()).create(any(newsModel.class));
    }

    @Test
    void givenNewsDtoRequest_whenUpdate_thenReturnNewsDtoResponse(){
//        AuthorModel authorModel = new AuthorModel();
//        UpdateNewsDtoRequest updateNewsDtoRequest = new UpdateNewsDtoRequest("COMMERCE AND TRADE",
//                "A landlord's heartwarming Christmas present.",
//                "John Lennon",
//                new ArrayList<>(),
//                new ArrayList<>());
//        given(newsRepository.update(newsModel)).willReturn(newsModel);
//        given(mapper.modelToDto(newsModel)).willReturn(newsDtoResponse);
//        given(mapper.dtoToModel(newsDtoRequest)).willReturn(newsModel);
//        given(newsRepository.existById(2L)).willReturn(true);
//        given(authorRepository.readByName(authorDtoResponse.name())).willReturn(Optional.of(newsModel.getAuthorModel()));
//        given(authorRepository.create(authorModel)).willReturn(authorModel);
//        NewsDtoResponse newsDtoResponse1 = newsService.update(2L, updateNewsDtoRequest);
//        assertThat(newsDtoResponse1.getAuthorDto().name()).isEqualTo("John Lennon");
//        assertThat(newsDtoResponse1.getId()).isEqualTo(2L);
//        assertThat(newsDtoResponse1.getTitle()).isEqualTo("COMMERCE AND TRADE");
    }

    @Test
    void given_whenUpdate_thenThrowsNotFoundException(){
        UpdateNewsDtoRequest updateNewsDtoRequest = new UpdateNewsDtoRequest("COMMERCE AND TRADE",
                "A landlord's heartwarming Christmas present.",
                "John Lennon",
                new ArrayList<>(),
                new ArrayList<>());
        given(newsRepository.existById(2L)).willReturn(false);

        Assertions.assertThrows(NotFoundException.class, () -> newsService.update(2L, updateNewsDtoRequest));
    }

    @Test
    void givenNewsDtoRequest_whenDelete_thenReturnNothing(){
        long newsId = 1L;
        given(newsRepository.existById(newsId)).willReturn(true);
        willDoNothing().given(newsRepository).deleteById(newsId);
        newsService.deleteById(newsId);

        verify(newsRepository, times(1)).deleteById(newsId);
    }

    @Test
    void givenNewsDtoRequest_whenDelete_thenThrowsNotFoundException(){
        long newsId = 1L;
        given(newsRepository.existById(newsId)).willReturn(false);

        Assertions.assertThrows(NotFoundException.class, () -> newsService.deleteById(newsId));
    }


}
