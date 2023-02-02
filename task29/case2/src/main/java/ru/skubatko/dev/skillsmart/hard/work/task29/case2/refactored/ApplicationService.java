package ru.skubatko.dev.skillsmart.hard.work.task29.case2.refactored;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ApplicationService {
    private final MessageService messageService;
    private final DictionaryMapperService dictionaryMapperService;

    public ApplicationDTO findByApplicationId(String xEmployeePIN, Long applicationId) {
        return applicationRepository.findById(applicationId)
            .map(app -> ApplicationMapper.INSTANCE.toDto(app))
            .orElseThrow(() -> new AppNotFoundException(
                Errors.APPLICATION_NOT_FOUND,
                messageService.getLocalizedMessage("application.not_found.alpp-comn-view-1") +
                    messageService.getLocalizedMessage("application.value.applicationId", applicationId.toString())));
    }

    public ApplicationDTO findByApplicationIdEnhanced(String xEmployeePIN, Long applicationId) {
        return applicationRepository.findById(applicationId)
            .map(app -> ApplicationMapper.INSTANCE.toMappedDto(app, dictionaryMapperService))
            .orElseThrow(() -> new AppNotFoundException(
                Errors.APPLICATION_NOT_FOUND,
                messageService.getLocalizedMessage("application.not_found.alpp-comn-view-1") +
                    messageService.getLocalizedMessage("application.value.applicationId", applicationId.toString())));
    }
}
