package ru.skubatko.dev.skillsmart.hard.work.task27.case2.refactored;

public interface ModifyCommentService {

    CommentDTO modify(String xEmployeePIN, String xEmployeeLogin, Long applicationId, Long commentId, CommentInputDTO comment);
}
