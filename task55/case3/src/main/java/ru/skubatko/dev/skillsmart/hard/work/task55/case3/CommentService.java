package ru.skubatko.dev.skillsmart.hard.work.task55.case3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ApplicationRepository applicationRepository;
    private final MessageService messageService;

    public void delete(String xEmployeePIN, Long applicationId, Long commentId) {
        ApplicationEntity application = null;

        // поиск заявок
        () -> {
            application = applicationRepository
                .findByApplicationId(applicationId)
                .orElseThrow(() -> new AppNotFoundException(
                    Errors.APPLICATION_NOT_FOUND,
                    messageService.getLocalizedMessage("application.not_found.alpp-comn-view-1") +
                        messageService.getLocalizedMessage("application.value.applicationId", applicationId.toString()))
                );
        }

        CommentEntity comment = commentRepository.findByCommentIdAndUserCode(commentId, xEmployeePIN);

        // проверка на найденные комментарии
        () -> {
            if (Objects.isNull(comment) || !comment.getApplication().equals(application)) {
                throw new AppNotFoundException(
                    Errors.COMMENT_NOT_FOUND,
                    messageService.getLocalizedMessage("comment.not_found.alpp-comn-view-6", commentId.toString())
                );
            }
        }

        commentRepository.delete(comment);
    }
}
