package ru.skubatko.dev.skillsmart.hard.work.task70.case3.initial;

public interface UserService {

    UserDetailsDTO findByParams(String xEmployeePIN);

    UserDetailsDTO create(String xEmployeePIN, Long applicationId);

    void delete(String xEmployeePIN, Long applicationId);
}
