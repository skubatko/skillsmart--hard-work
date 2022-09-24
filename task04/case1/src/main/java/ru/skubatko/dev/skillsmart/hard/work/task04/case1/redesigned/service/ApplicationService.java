package ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.service;

import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain.Application;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain.ApplicationId;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain.User;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.exceptions.ApplicationNotFoundException;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public List<Application> findByUser(User user) {
        return applicationRepository.findByUser(user);
    }

    public void bind(ApplicationId applicationId, User user) {
        Application application = applicationRepository.findById(applicationId)
            .orElseThrow(ApplicationNotFoundException::new);
        applicationRepository.update(applicationId, new Application(application, user));
    }

    public void delete(ApplicationId applicationId) {
        applicationRepository.delete(applicationId);
    }
}
