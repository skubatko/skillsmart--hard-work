package ru.skubatko.dev.skillsmart.hard.work.task70.case1.refactored;

public interface UpdateApplicationService {

    ApplicationOutputDTO addNewState(Long externalId, StateHistoryDTO state) throws AppException;

    ApplicationOutputDTO addNewState(String number, StateHistoryDTO state) throws AppException;
}
