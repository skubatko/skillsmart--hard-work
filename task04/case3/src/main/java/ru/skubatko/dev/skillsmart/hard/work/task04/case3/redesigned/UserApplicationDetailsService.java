package ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned;

import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.application.Application;
import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.application.ApplicationCreationChannel;
import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.application.ApplicationLiabilityType;
import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.application.ApplicationRegion;
import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.application.ApplicationSalesChannel;
import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.application.ApplicationSalesOffice;
import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.application.ApplicationSecondLevelSalesChannel;
import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.application.ApplicationStatus;
import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.user.User;
import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.user.UserCode;
import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.service.ApplicationService;
import ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserApplicationDetailsService {
    private final UserService userService;
    private final ApplicationService applicationService;

    public List<ApplicationStatus> getApplicationStatusList(UserCode userCode) {
        User user = userService.findByUserCode(userCode);
        List<Application> applications = applicationService.findByUser(user);
        return applications.stream().map(Application::getStatus).collect(Collectors.toList());
    }

    public List<ApplicationRegion> getApplicationRegionList(UserCode userCode) {
        User user = userService.findByUserCode(userCode);
        List<Application> applications = applicationService.findByUser(user);
        return applications.stream().map(Application::getRegion).collect(Collectors.toList());
    }

    public List<ApplicationCreationChannel> getApplicationCreationChannelList(UserCode userCode) {
        User user = userService.findByUserCode(userCode);
        List<Application> applications = applicationService.findByUser(user);
        return applications.stream().map(Application::getCreationChannel).collect(Collectors.toList());
    }

    public List<ApplicationSalesChannel> getApplicationSalesChannelList(UserCode userCode) {
        User user = userService.findByUserCode(userCode);
        List<Application> applications = applicationService.findByUser(user);
        return applications.stream().map(Application::getSalesChannel).collect(Collectors.toList());
    }

    public List<ApplicationSecondLevelSalesChannel> getApplicationSecondLevelSalesChannelList(UserCode userCode) {
        User user = userService.findByUserCode(userCode);
        List<Application> applications = applicationService.findByUser(user);
        return applications.stream().map(Application::getSecondLevelSalesChannel).collect(Collectors.toList());
    }

    public List<ApplicationSalesOffice> getApplicationSalesOfficeList(UserCode userCode) {
        User user = userService.findByUserCode(userCode);
        List<Application> applications = applicationService.findByUser(user);
        return applications.stream().map(Application::getSalesOffice).collect(Collectors.toList());
    }

    public List<ApplicationLiabilityType> getApplicationLiabilityTypeList(UserCode userCode) {
        User user = userService.findByUserCode(userCode);
        List<Application> applications = applicationService.findByUser(user);
        return applications.stream().map(Application::getLiabilityType).collect(Collectors.toList());
    }
}
