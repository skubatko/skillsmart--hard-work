package ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.user;

import lombok.Value;

@Value
public class User {
    UserCode code;

    public static final User NONE = new User(UserCode.NONE);
}
