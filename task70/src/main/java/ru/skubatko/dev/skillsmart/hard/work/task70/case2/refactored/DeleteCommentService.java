package ru.skubatko.dev.skillsmart.hard.work.task70.case2.refactored;

public interface DeleteCommentService {

    void delete(String xEmployeePIN, Long applicationId, Long commentId);
}
