package ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain;

import lombok.Value;

@Value
public class Application {
    ApplicationId id;
    User user;

    public static final Application NONE = new Application(ApplicationId.NONE, User.NONE);

    public Application(ApplicationId id, User user) {
        this.id = id;
        this.user = user;
    }

    public Application(Application application, User user) {
        this.id = application.id;
        this.user = user;
    }
}
