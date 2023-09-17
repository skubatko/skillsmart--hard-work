package ru.skubatko.dev.skillsmart.hard.work.task70.case2.refactored;

public interface UpdateCommentService {

    CommentDTO modify(String xEmployeePIN, String xEmployeeLogin, Long applicationId, Long commentId, CommentInputDTO comment);
}
