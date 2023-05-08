package ru.skubatko.dev.skillsmart.hard.work.task43.case2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@DisplayName("Сервис управления комментариями")
@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private CommentService commentService;

    @DisplayName("должен создавать комментарий")
    @Test
    void whenCreate_thenCommentCreated() {
        ApplicationEntity application = getTestApplicationEntity();
        CommentEntity comment = createTestComment(USER_CODE1, USER_LOGIN_1, application);
        CommentInputDTO commentInput = new CommentInputDTO();
        commentInput.setComment(comment.getComment());
        when(applicationRepository.findByApplicationId(APPLICATION_ID)).thenReturn(Optional.of(application));
        when(commentRepository.save(any())).thenReturn(comment);

        commentService.create(USER_CODE1, USER_LOGIN_1, APPLICATION_ID, commentInput);

        verify(commentRepository, times(1)).save(any());
    }
}
