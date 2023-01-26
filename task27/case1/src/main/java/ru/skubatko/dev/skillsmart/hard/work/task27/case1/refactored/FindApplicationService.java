package ru.skubatko.dev.skillsmart.hard.work.task27.case1.refactored;

import java.awt.print.Pageable;

public interface FindApplicationService {

    Iterable<ApplicationDTO> findByParams(String xEmployeePIN,
            String xEmployeeLogin, ApplicationParametersDTO parametersDTO,
            boolean mapped, Pageable pageable) throws AppException;

    Iterable<ApplicationInfoDTO> findAppInfoByParams(
            String xEmployeePIN,
            ApplicationInfoParametersDTO parametersDTO,
            boolean mapped, Pageable pageable) throws AppException;

    ApplicationDTO findByApplicationId(String xEmployeePIN, Long applicationId, boolean mapped) throws AppException;

    ApplicationDTO findByNumber(String number, boolean mapped);
}
