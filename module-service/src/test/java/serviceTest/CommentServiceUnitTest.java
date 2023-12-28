package serviceTest;

import com.mjc.school.repository.exception.EntityConflictRepositoryException;
import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.repository.interfaces.CommentRepository;
import com.mjc.school.repository.interfaces.NewsRepository;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ResourceConflictServiceException;
import com.mjc.school.service.implementation.CommentService;
import com.mjc.school.service.interfaces.CommentModelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceUnitTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private CommentModelMapper mapper;
    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private CommentService commentService;

    private Comment comment;
    private CommentDtoRequest commentDtoRequest;
    private CommentDtoResponse commentDtoResponse;


//    @BeforeEach
//    void setup(){
//        comment = new Comment();
//        commentDtoRequest = new CommentDtoRequest("Hahahah", 12L);
//        commentDtoResponse = new CommentDtoResponse(2L, "Hahahah", 12L, LocalDateTime.now(), LocalDateTime.now());
//    }

//    @Test
//    public void givenPageDto_whenReadAll_thenReturnPageDtoResponse(){
//        ResourceSearchFilter resourceSearchFilter = mock(ResourceSearchFilter.class);
//        ResourceSearchFilterRequestDTO resourceSearchFilterRequestDTO = mock(ResourceSearchFilterRequestDTO.class);
//        PageDtoResponse modelPageDtoResponse = mock(PageDtoResponse.class);
//        when(commentService.readAll(resourceSearchFilterRequestDTO)).thenReturn(modelPageDtoResponse);
//        Assertions.assertEquals(commentService.readAll(resourceSearchFilterRequestDTO), modelPageDtoResponse);
//    }
//
//    @Test
//    void givenCommentDto_whenReadById_thenReturnCommentDtoResponse(){
//        given(commentRepository.findById(commentDtoResponse.id())).willReturn(Optional.of(comment));
//        given(mapper.modelToDto(comment)).willReturn(commentDtoResponse);
//
//        CommentDtoResponse commentDtoResponse1 = commentService.readById(commentDtoResponse.id());
//        assertThat(commentDtoResponse1.content()).isEqualTo("Hahahah");
//    }
//
//    @Test
//    void givenCommentDto_whenReadById_thenReturnNotFoundException(){
//        given(commentRepository.findById(999L)).willThrow(NotFoundException.class);
//
//        Assertions.assertThrows(NotFoundException.class, () -> commentService.readById(999L));
//    }
//
//    @Test
//    void givenComment_whenCreate_thenReturnCommentDtoResponse(){
//        given(commentRepository.save(comment)).willReturn(comment);
//        given(mapper.dtoToModel(commentDtoRequest)).willReturn(comment);
//        given(mapper.modelToDto(comment)).willReturn(commentDtoResponse);
//        given(newsRepository.existsById(commentDtoRequest.newsId())).willReturn(true);
//        CommentDtoResponse commentDtoResponse1 = commentService.create(commentDtoRequest);
//
//        assertThat(commentDtoResponse1.content()).isEqualTo("Hahahah");
//        assertThat(commentDtoResponse1.id()).isEqualTo(2L);
//    }

//    @Test
//    void givenCommentDtoRequest_whenCreate_thenThrowsEntityConflictRepositoryException(){
//        given(commentRepository.save(comment)).willThrow(EntityConflictRepositoryException.class);
//        given(mapper.dtoToModel(commentDtoRequest)).willReturn(comment);
//        given(newsRepository.existsById(commentDtoRequest.newsId())).willReturn(true);
//        Assertions.assertThrows(ResourceConflictServiceException.class, () -> commentService.create(commentDtoRequest));
////        verify(commentRepository, never()).create(any(commentModel.class));
//    }

//    @Test
//    void givenCommentDtoRequest_whenUpdate_thenReturnCommentDtoResponse(){
//        given(commentRepository.save(comment)).willReturn(comment);
//        given(mapper.modelToDto(comment)).willReturn(commentDtoResponse);
//        given(mapper.dtoToModel(commentDtoRequest)).willReturn(comment);
//        given(commentRepository.existsById(2L)).willReturn(true);
//
//        CommentDtoResponse commentDtoResponse1 = commentService.update(2L, commentDtoRequest);
//        assertThat(commentDtoResponse1.content()).isEqualTo("Hahahah");
//        assertThat(commentDtoResponse1.id()).isEqualTo(2L);
//    }

//    @Test
//    void given_whenUpdate_thenThrowsNotFoundException(){
//        given(commentRepository.existsById(2L)).willReturn(false);
//
//        Assertions.assertThrows(NotFoundException.class, () -> commentService.update(2L, commentDtoRequest));
//    }

//    @Test
//    void givenCommentDtoRequest_whenDelete_thenReturnNothing(){
//        long commentId = 1L;
//        given(commentRepository.existsById(commentId)).willReturn(true);
//        willDoNothing().given(commentRepository).deleteById(commentId);
//        commentService.deleteById(commentId);
//
//        verify(commentRepository, times(1)).deleteById(commentId);
//    }
//
//    @Test
//    void givenCommentDtoRequest_whenDelete_thenThrowsNotFoundException(){
//        long commentId = 1L;
//        given(commentRepository.existsById(commentId)).willReturn(false);
//
//        Assertions.assertThrows(NotFoundException.class, () -> commentService.deleteById(commentId));
//    }
//
//    @Test
//    void givenComment_whenReadByNewsId_thenReturnCommentDtoResponse(){
//        List<Comment> comments = List.of(comment, new Comment());
//        given(commentRepository.findByNewsModelId(1L)).willReturn(comments);
//        given(mapper.modelListToDtoList(comments)).willReturn(List.of(commentDtoResponse, commentDtoResponse));
//
//        List<CommentDtoResponse> commentDtoResponseList = commentService.readByNewsId(1L);
//        assertThat(commentDtoResponseList.size()).isEqualTo(2);
//        assertThat(commentDtoResponse).isNotNull();
//    }



}
