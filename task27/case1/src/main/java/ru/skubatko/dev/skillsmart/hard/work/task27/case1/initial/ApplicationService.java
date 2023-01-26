package ru.skubatko.dev.skillsmart.hard.work.task27.case1.initial;

import java.awt.print.Pageable;

public interface ApplicationService {

    ApplicationOutputDTO save(String number, ApplicationInputDTO applicationInputDTO) throws AppException;

    void savePlain(String number, ApplicationInputDTO applicationInputDTO) throws AppException;

    ApplicationOutputDTO addNewState(Long externalId, StateHistoryDTO state) throws AppException;

    ApplicationOutputDTO addNewState(String number, StateHistoryDTO state) throws AppException;

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
