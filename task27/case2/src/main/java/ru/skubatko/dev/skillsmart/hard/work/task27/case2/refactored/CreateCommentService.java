package ru.skubatko.dev.skillsmart.hard.work.task27.case2.refactored;

public interface CreateCommentService {

    CommentDTO create(String xEmployeePIN, String xEmployeeLogin, Long applicationId, CommentInputDTO comment);
}
