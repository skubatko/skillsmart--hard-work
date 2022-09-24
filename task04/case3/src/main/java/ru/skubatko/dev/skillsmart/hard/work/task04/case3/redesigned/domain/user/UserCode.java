package ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.user;

import lombok.Value;

@Value
public class UserCode {
    public static final UserCode NONE = new UserCode();
}
