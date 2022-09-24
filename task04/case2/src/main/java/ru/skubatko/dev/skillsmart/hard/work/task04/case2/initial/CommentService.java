package ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial;

import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.domain.ApplicationEntity;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.domain.CommentEntity;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.dto.CommentDTO;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.dto.CommentInputDTO;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.dto.CommentOutputDTO;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.enums.Errors;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.exceptions.AppNotFoundException;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.mappers.CommentMapper;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.repository.ApplicationRepository;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.repository.CommentRepository;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.service.MessageService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ApplicationRepository applicationRepository;
    private final MessageService messageService;

    public List<CommentOutputDTO> findByParams(String xEmployeePIN, Long applicationId) {
        List<CommentOutputDTO> commentsDTO = new ArrayList<>();
        CommentOutputDTO commentDTO;
        ApplicationEntity application = getApplicationById(applicationId);
        List<CommentEntity> commentEntityList = application.getComments();

        boolean editModeFlag;
        for (CommentEntity commentVal : commentEntityList) {
            commentDTO = CommentMapper.INSTANCE.toOutputDto(commentVal);
            editModeFlag = xEmployeePIN.equals(commentVal.getUserCode());
            commentDTO.setEditMode(editModeFlag);
            commentsDTO.add(commentDTO);
        }
        return commentsDTO;
    }

    public CommentDTO create(
        String xEmployeePIN, String xEmployeeLogin,
        Long applicationId, CommentInputDTO comment
    ) {
        String commentVal = comment.getComment();
        LocalDateTime commentDate = LocalDateTime.now();

        ApplicationEntity application = getApplicationById(applicationId);

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUserCode(xEmployeePIN);
        commentEntity.setUserLogin(xEmployeeLogin);
        commentEntity.setComment(commentVal);
        commentEntity.setCommentDate(commentDate);
        commentEntity.setApplication(application);

        commentRepository.save(commentEntity);
        return CommentMapper.INSTANCE.toDto(commentEntity);
    }

    public CommentDTO modify(
        String xEmployeePIN, String xEmployeeLogin,
        Long applicationId, Long commentId,
        CommentInputDTO comment
    ) {
        String commentVal = comment.getComment();
        LocalDateTime commentDate = LocalDateTime.now();

        ApplicationEntity application = getApplicationById(applicationId);

        CommentEntity commentEntity = commentRepository.findByCommentId(commentId);
        checkIfCommentNotFoundForApp(commentEntity, commentId, application);

        commentEntity.setUserCode(xEmployeePIN);
        commentEntity.setUserLogin(xEmployeeLogin);
        commentEntity.setComment(commentVal);
        commentEntity.setCommentDate(commentDate);

        commentRepository.save(commentEntity);
        return CommentMapper.INSTANCE.toDto(commentEntity);
    }

    public void delete(String xEmployeePIN, Long applicationId, Long commentId) {
        ApplicationEntity application = getApplicationById(applicationId);

        CommentEntity comment = commentRepository.findByCommentIdAndUserCode(commentId, xEmployeePIN);
        checkIfCommentNotFoundForApp(comment, commentId, application);

        commentRepository.delete(comment);
    }

    private ApplicationEntity getApplicationById(Long applicationId) {
        return applicationRepository
            .findByApplicationId(applicationId)
            .orElseThrow(() -> new AppNotFoundException(
                Errors.APPLICATION_NOT_FOUND,
                messageService.getLocalizedMessage("application.not_found.alpp-comn-view-1") +
                    messageService.getLocalizedMessage("application.value.applicationId", applicationId.toString()))
            );
    }

    private void checkIfCommentNotFoundForApp(
        CommentEntity commentEntity,
        Long commentId,
        ApplicationEntity application) {
        if (Objects.isNull(commentEntity) || !commentEntity.getApplication().equals(application)) {
            throw new AppNotFoundException(
                Errors.COMMENT_NOT_FOUND,
                messageService.getLocalizedMessage("comment.not_found.alpp-comn-view-6", commentId.toString())
            );
        }
    }
}
