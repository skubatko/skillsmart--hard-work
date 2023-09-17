package ru.skubatko.dev.skillsmart.hard.work.task70.case3.refactored;

public interface PersistUserService {

    UserDetailsDTO create(String xEmployeePIN, Long applicationId);
}
