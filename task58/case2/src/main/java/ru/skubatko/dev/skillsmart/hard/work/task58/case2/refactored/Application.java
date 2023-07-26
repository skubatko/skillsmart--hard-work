package ru.skubatko.dev.skillsmart.hard.work.task58.case2.refactored;

public class Application {

    public ApplicationStatus changeStatus(ApplicationStatus currentStatus) {
        switch (currentStatus) {
            case DRAFT:
                return ApplicationStatus.OPEN;
            case OPEN:
                return ApplicationStatus.CLOSED;
            default:
                return ApplicationStatus.DRAFT;
        }
    }
}
