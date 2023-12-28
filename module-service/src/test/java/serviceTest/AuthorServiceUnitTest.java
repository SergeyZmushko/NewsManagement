package serviceTest;

import com.mjc.school.repository.exception.EntityConflictRepositoryException;
import com.mjc.school.repository.interfaces.AuthorRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.implementation.AuthorService;
import com.mjc.school.service.interfaces.AuthorModelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceUnitTest {

    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private AuthorModelMapper mapper;

    @InjectMocks
    private AuthorService authorService;

    private AuthorModel authorModel;
    private AuthorDtoRequest authorDtoRequest;
    private AuthorDtoResponse authorDtoResponse;


    @BeforeEach
    public void setup(){
        authorModel = new AuthorModel();
        authorDtoRequest = new AuthorDtoRequest("John Lennon");
        authorDtoResponse = new AuthorDtoResponse(2L, "John Lennon");
    }

//    @Test
//    public void givenPageDto_whenReadAll_thenReturnPageDtoResponse(){
//        ResourceSearchFilter resourceSearchFilter = mock(ResourceSearchFilter.class);
//        ResourceSearchFilterRequestDTO resourceSearchFilterRequestDTO = mock(ResourceSearchFilterRequestDTO.class);
//        PageDtoResponse modelPageDtoResponse = mock(PageDtoResponse.class);
//        when(authorService.readAll(resourceSearchFilterRequestDTO)).thenReturn(modelPageDtoResponse);
//        Assertions.assertEquals(authorService.readAll(resourceSearchFilterRequestDTO), modelPageDtoResponse);
//    }

//    @Test
//    void givenAuthorDto_whenReadById_thenReturnAuthorDtoResponse(){
//        given(authorRepository.readById(authorDtoResponse.id())).willReturn(Optional.of(authorModel));
//        given(mapper.modelToDto(authorModel)).willReturn(authorDtoResponse);
//
//        AuthorDtoResponse authorDtoResponse1 = authorService.readById(authorDtoResponse.id());
//        assertThat(authorDtoResponse1.name()).isEqualTo("John Lennon");
//    }
//
//    @Test
//    void givenAuthorDto_whenReadById_thenReturnNotFoundException(){
//        given(authorRepository.readById(999L)).willThrow(NotFoundException.class);
//
//        Assertions.assertThrows(NotFoundException.class, () -> authorService.readById(999L));
//    }
//
//    @Test
//    void givenAuthor_whenCreate_thenReturnAuthorDtoResponse(){
//        given(authorRepository.create(authorModel)).willReturn(authorModel);
//        given(mapper.dtoToModel(authorDtoRequest)).willReturn(authorModel);
//        given(mapper.modelToDto(authorModel)).willReturn(authorDtoResponse);
//
//        AuthorDtoResponse authorDtoResponse1 = authorService.create(authorDtoRequest);
//
//        assertThat(authorDtoResponse1.name()).isEqualTo("John Lennon");
//        assertThat(authorDtoResponse1.id()).isEqualTo(2L);
//    }
//
//    @Test
//    void givenAuthorDtoRequest_whenCreate_thenThrowsEntityConflictRepositoryException(){
//        given(authorRepository.create(authorModel)).willThrow(EntityConflictRepositoryException.class);
//        given(mapper.dtoToModel(authorDtoRequest)).willReturn(authorModel);
//        Assertions.assertThrows(ResourceConflictServiceException.class, () -> authorService.create(authorDtoRequest));
////        verify(authorRepository, never()).create(any(AuthorModel.class));
//    }
//
//    @Test
//    void givenAuthorDtoRequest_whenUpdate_thenReturnAuthorDtoResponse(){
//        given(authorRepository.update(authorModel)).willReturn(authorModel);
//        given(mapper.modelToDto(authorModel)).willReturn(authorDtoResponse);
//        given(mapper.dtoToModel(authorDtoRequest)).willReturn(authorModel);
//        given(authorRepository.existById(2L)).willReturn(true);
//
//        AuthorDtoResponse authorDtoResponse1 = authorService.update(2L, authorDtoRequest);
//        assertThat(authorDtoResponse1.name()).isEqualTo("John Lennon");
//        assertThat(authorDtoResponse1.id()).isEqualTo(2L);
//    }
//
//    @Test
//    void given_whenUpdate_thenThrowsNotFoundException(){
//        given(authorRepository.existById(2L)).willReturn(false);
//
//        Assertions.assertThrows(NotFoundException.class, () -> authorService.update(2L, authorDtoRequest));
//    }
//
//    @Test
//    void givenAuthorDtoRequest_whenDelete_thenReturnNothing(){
//        long authorId = 1L;
//        given(authorRepository.existById(authorId)).willReturn(true);
//        willDoNothing().given(authorRepository).deleteById(authorId);
//        authorService.deleteById(authorId);
//
//        verify(authorRepository, times(1)).deleteById(authorId);
//    }
//
//    @Test
//    void givenAuthorDtoRequest_whenDelete_thenThrowsNotFoundException(){
//        long authorId = 1L;
//        given(authorRepository.existById(authorId)).willReturn(false);
//
//        Assertions.assertThrows(NotFoundException.class, () -> authorService.deleteById(authorId));
//    }
//
//    @Test
//    void givenAuthor_whenReadByNewsId_thenReturnAuthorDtoResponse(){
//        given(authorRepository.readByNewsId(1L)).willReturn(Optional.of(authorModel));
//        given(mapper.modelToDto(authorModel)).willReturn(authorDtoResponse);
//
//        AuthorDtoResponse authorDtoResponse = authorService.readByNewsId(1L);
//        assertThat(authorDtoResponse.name()).isEqualTo("John Lennon");
//        assertThat(authorDtoResponse).isNotNull();
//    }
//
//    @Test
//    void givenAuthorDtoRequest_whenReadByNewsId_thenThrowsNotFoundException(){
//        long authorId = 1L;
//        given(authorRepository.readByNewsId(authorId)).willReturn(Optional.empty());
//
//        Assertions.assertThrows(NotFoundException.class, () -> authorService.readByNewsId(authorId));
//
//    }


}
