package ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.repository;

import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain.Application;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain.ApplicationId;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain.User;

import java.util.List;
import java.util.Optional;

public class ApplicationRepository {
    public List<Application> findByUser(User user) {
        return null;
    }

    public Optional<Application> findById(ApplicationId applicationId) {
        return null;
    }

    public void update(ApplicationId applicationId, Application application) {
    }

    public void delete(ApplicationId applicationId) {

    }
}
