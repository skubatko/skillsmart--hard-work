package ru.skubatko.dev.skillsmart.hard.work.task27.case3.refactored;

public interface CreateUserService {

    UserDetailsDTO create(String xEmployeePIN, Long applicationId);
}
