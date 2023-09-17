package ru.skubatko.dev.skillsmart.hard.work.task70.case2.initial;

public interface CommentService {

    Iterable<CommentOutputDTO> findByParams(String xEmployeePIN, Long applicationId);

    CommentDTO create(String xEmployeePIN, String xEmployeeLogin, Long applicationId, CommentInputDTO comment);

    CommentDTO modify(String xEmployeePIN, String xEmployeeLogin, Long applicationId, Long commentId, CommentInputDTO comment);

    void delete(String xEmployeePIN, Long applicationId, Long commentId);
}
