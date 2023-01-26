package ru.skubatko.dev.skillsmart.hard.work.task27.case1.refactored;

import java.awt.print.Pageable;

public interface SaveApplicationService {

    ApplicationOutputDTO save(String number, ApplicationInputDTO applicationInputDTO) throws AppException;

    void savePlain(String number, ApplicationInputDTO applicationInputDTO) throws AppException;
}
