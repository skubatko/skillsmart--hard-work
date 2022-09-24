package ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.domain;

import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class User {
    List<Application> applications;

    public static final User NONE = new User(Collections.emptyList());
}
