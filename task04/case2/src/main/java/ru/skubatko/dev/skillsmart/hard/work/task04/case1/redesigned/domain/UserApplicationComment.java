package ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain;

import lombok.Value;

@Value
public class UserApplicationComment {
    String text;
    User user;
    Application application;

    public UserApplicationComment(UserApplicationComment comment, User user, Application application) {
        this.text = comment.text;
        this.user = user;
        this.application = application;
    }
}
