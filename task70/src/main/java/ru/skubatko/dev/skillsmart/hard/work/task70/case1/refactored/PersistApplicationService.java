package ru.skubatko.dev.skillsmart.hard.work.task70.case1.refactored;

public interface PersistApplicationService {

    ApplicationOutputDTO save(String number, ApplicationInputDTO applicationInputDTO) throws AppException;

    void savePlain(String number, ApplicationInputDTO applicationInputDTO) throws AppException;
}
