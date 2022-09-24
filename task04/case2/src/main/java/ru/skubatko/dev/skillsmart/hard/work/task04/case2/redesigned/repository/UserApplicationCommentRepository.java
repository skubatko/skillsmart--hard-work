package ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.repository;

import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.domain.Application;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.domain.User;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.domain.UserApplicationComment;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.domain.UserApplicationCommentId;

import java.util.List;
import java.util.Optional;

public class UserApplicationCommentRepository {

    public Optional<UserApplicationComment> findById(UserApplicationCommentId id) {
        return null;
    }

    public List<UserApplicationComment> findByUserAndApplication(User user, Application application) {
        return null;
    }

    public void create(UserApplicationComment comment) {}

    public void update(UserApplicationCommentId id, UserApplicationComment comment) {}

    public void delete(UserApplicationCommentId id) {}
}
