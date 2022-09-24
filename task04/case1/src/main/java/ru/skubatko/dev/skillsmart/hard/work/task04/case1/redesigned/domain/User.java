package ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain;

import lombok.Value;

@Value
public class User {
    public static final User NONE = new User();
}
