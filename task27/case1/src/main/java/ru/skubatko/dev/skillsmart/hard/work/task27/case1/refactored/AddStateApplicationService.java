package ru.skubatko.dev.skillsmart.hard.work.task27.case1.refactored;

import java.awt.print.Pageable;

public interface AddStateApplicationService {

    ApplicationOutputDTO addNewState(Long externalId, StateHistoryDTO state) throws AppException;

    ApplicationOutputDTO addNewState(String number, StateHistoryDTO state) throws AppException;
}
