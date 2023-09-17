package ru.skubatko.dev.skillsmart.hard.work.task70.case2.refactored;

public interface PersistCommentService {

    CommentDTO create(String xEmployeePIN, String xEmployeeLogin, Long applicationId, CommentInputDTO comment);
}
