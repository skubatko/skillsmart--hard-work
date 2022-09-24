package ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned;

import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain.Application;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain.ApplicationId;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain.User;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain.UserApplication;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain.UserCode;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.service.ApplicationService;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserApplicationService {
    private final UserService userService;
    private final ApplicationService applicationService;

    public UserApplication findByUserCode(UserCode userCode) {
        User user = userService.findByUserCode(userCode);
        List<Application> applications = applicationService.findByUser(user);
        return new UserApplication(user, applications);
    }

    public void add(UserCode userCode, ApplicationId applicationId) {
        User user = userService.findByUserCode(userCode);
        applicationService.bind(applicationId, user);
    }

    public void delete(UserCode userCode, ApplicationId applicationId) {
        User user = userService.findByUserCode(userCode);
        List<Application> applications = applicationService.findByUser(user);
        if (isPresent(applicationId, applications)) {
            applicationService.delete(applicationId);
        }
    }

    private boolean isPresent(ApplicationId applicationId, List<Application> applications) {
        return applications.stream().map(Application::getId).anyMatch(it -> it.equals(applicationId));
    }
}
