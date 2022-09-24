package ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain;

import lombok.Value;

import java.util.List;

@Value
public class UserApplication {
    User user;
    List<Application> applications;
}
