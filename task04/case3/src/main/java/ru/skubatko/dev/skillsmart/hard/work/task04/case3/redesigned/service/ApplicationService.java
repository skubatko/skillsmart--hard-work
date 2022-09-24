package ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.service;

import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.application.Application;
import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.user.User;
import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public List<Application> findByUser(User user) {
        return applicationRepository.findByUser(user);
    }
}
