package ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain;

import lombok.Value;

@Value
public class ApplicationId {
    public static final ApplicationId NONE = new ApplicationId();
}
