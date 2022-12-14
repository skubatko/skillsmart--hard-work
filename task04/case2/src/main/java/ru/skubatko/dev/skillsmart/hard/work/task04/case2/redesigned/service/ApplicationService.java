package ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.service;

import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.domain.Application;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.domain.ApplicationId;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.redesigned.repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public Application findById(ApplicationId applicationId) {
        return applicationRepository.findById(applicationId).orElse(Application.NONE);
    }
}
