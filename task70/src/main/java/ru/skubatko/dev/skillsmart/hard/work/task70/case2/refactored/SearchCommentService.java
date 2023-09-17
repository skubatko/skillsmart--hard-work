package ru.skubatko.dev.skillsmart.hard.work.task70.case2.refactored;

public interface SearchCommentService {

    Iterable<CommentOutputDTO> findByParams(String xEmployeePIN, Long applicationId);
}
