package ru.skubatko.dev.skillsmart.hard.work.task27.case2.refactored;

public interface FindCommentService {

    Iterable<CommentOutputDTO> findByParams(String xEmployeePIN, Long applicationId);
}
