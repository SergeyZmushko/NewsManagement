package servicesMockTests;

import com.mjc.school.interfaces.AuthorRepository;
import com.mjc.school.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.implementation.AuthorService;
import com.mjc.school.service.interfaces.AuthorModelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthorServiceMockTest {

    @Mock
    private AuthorRepository repository;

    @Mock
    private AuthorModelMapper mapper;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void createAuthorTest() {
        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest("name");
        AuthorModel authorModel = new AuthorModel();
        authorModel.setId(1L);
        authorModel.setName("name");
        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse(1L, "name");
        when(mapper.dtoToModel(authorDtoRequest)).thenReturn(authorModel);
        when(repository.save(authorModel)).thenReturn(authorModel);
        when(mapper.modelToDto(authorModel)).thenReturn(authorDtoResponse);

        AuthorDtoResponse response = authorService.create(authorDtoRequest);
        Assertions.assertEquals(response.id(), authorDtoResponse.id());
        Assertions.assertEquals(response.name(), authorDtoResponse.name());
    }

    @Test
    void readAuthorByIdTest() {
        AuthorModel authorModel = new AuthorModel();
        authorModel.setId(1L);
        authorModel.setName("name");

        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse(1L, "name");
        when(repository.findById(1L)).thenReturn(Optional.of(authorModel));
        when(mapper.modelToDto(authorModel)).thenReturn(authorDtoResponse);

        AuthorDtoResponse result = authorService.readById(1L);

        Assertions.assertEquals(result.id(), 1L);
        Assertions.assertEquals(result.name(), "name");
    }

    @Test
    void updateAuthorTest() {
        AuthorModel authorModel = new AuthorModel();
        authorModel.setId(1L);
        authorModel.setName("name");

        AuthorModel updatedAuthor = new AuthorModel();
        updatedAuthor.setId(1L);
        updatedAuthor.setName("New name");

        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest("New name");
        AuthorDtoResponse resultResponse = new AuthorDtoResponse(updatedAuthor.getId(), updatedAuthor.getName());


        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.of(authorModel));

        when(mapper.dtoToModel(authorDtoRequest)).thenReturn(authorModel);

        when(repository.save(authorModel)).thenReturn(updatedAuthor);
        when(mapper.modelToDto(updatedAuthor)).thenReturn(resultResponse);

        AuthorDtoResponse result = authorService.update(1L, authorDtoRequest);

        Assertions.assertEquals(1L, result.id());
        Assertions.assertEquals(result.name(), "New name");
    }


    @Test
    void updateAuthorThrowNotFoundExceptionTest() {
        AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest("Nick");
        when(repository.existsById(1L)).thenReturn(false);
        Assertions.assertThrows(NotFoundException.class, () -> authorService.update(1L, authorDtoRequest));
    }

    @Test
    void deleteTest() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(any());
        assertAll(() -> authorService.deleteById(1L));
    }

    @Test
    void readByNewsIdTest() {
        AuthorModel authorModel = new AuthorModel();
        authorModel.setName("name");
        authorModel.setId(1L);

        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse(1L, "name");
        when(repository.readByNewsId(1L)).thenReturn(Optional.of(authorModel));
        when(mapper.modelToDto(authorModel)).thenReturn(authorDtoResponse);

        AuthorDtoResponse response = authorService.readByNewsId(1L);

        Assertions.assertEquals(1L, response.id());
        Assertions.assertEquals("name", response.name());
    }
}