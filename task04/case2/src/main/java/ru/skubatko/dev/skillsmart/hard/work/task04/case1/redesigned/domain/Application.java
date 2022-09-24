package ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain;

import lombok.Value;

@Value
public class Application {
    ApplicationId id;

    public static final Application NONE = new Application(ApplicationId.NONE);
}
