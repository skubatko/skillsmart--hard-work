package ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned;

import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.domain.Application;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.domain.ApplicationId;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.domain.User;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.domain.UserApplicationComment;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.domain.UserApplicationCommentId;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.domain.UserCode;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.exceptions.UserApplicationCommentNotFoundException;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.repository.UserApplicationCommentRepository;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.service.ApplicationService;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserApplicationCommentService {
    private final UserApplicationCommentRepository userApplicationCommentRepository;
    private final UserService userService;
    private final ApplicationService applicationService;

    List<UserApplicationComment> findByUserCodeAndApplicationId(
        UserCode userCode,
        ApplicationId applicationId
    ) {
        User user = userService.findByUserCode(userCode);
        Application application = applicationService.findById(applicationId);
        return userApplicationCommentRepository.findByUserAndApplication(user, application);
    }

    public void create(
        UserApplicationComment comment,
        UserCode userCode,
        ApplicationId applicationId
    ) {
        User user = userService.findByUserCode(userCode);
        Application application = applicationService.findById(applicationId);
        if (user.getApplications().contains(application)) {
            userApplicationCommentRepository.create(new UserApplicationComment(comment, user, application));
        }
    }

    public void update(
        UserApplicationCommentId id,
        UserApplicationComment comment,
        UserCode userCode,
        ApplicationId applicationId
    ) {
        UserApplicationComment userApplicationComment = userApplicationCommentRepository.findById(id)
            .orElseThrow(UserApplicationCommentNotFoundException::new);
        User user = userService.findByUserCode(userCode);
        Application application = applicationService.findById(applicationId);
        if (userApplicationComment.getUser().equals(user)
            && userApplicationComment.getApplication().equals(application)) {
            userApplicationCommentRepository.update(id, comment);
        }
    }

    public void delete(
        UserApplicationCommentId id,
        UserCode userCode,
        ApplicationId applicationId
    ) {
        UserApplicationComment userApplicationComment = userApplicationCommentRepository.findById(id)
            .orElseThrow(UserApplicationCommentNotFoundException::new);
        User user = userService.findByUserCode(userCode);
        Application application = applicationService.findById(applicationId);
        if (userApplicationComment.getUser().equals(user)
            && userApplicationComment.getApplication().equals(application)) {
            userApplicationCommentRepository.delete(id);
        }
    }
}
