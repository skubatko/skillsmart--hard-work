package ru.skubatko.dev.skillsmart.hard.work.task04.case3.redesigned.domain.application;

import lombok.Value;

@Value
public class Application {
    ApplicationStatus status;
    ApplicationRegion region;
    ApplicationCreationChannel creationChannel;
    ApplicationSalesChannel salesChannel;
    ApplicationSecondLevelSalesChannel secondLevelSalesChannel;
    ApplicationSalesOffice salesOffice;
    ApplicationLiabilityType liabilityType;
}
