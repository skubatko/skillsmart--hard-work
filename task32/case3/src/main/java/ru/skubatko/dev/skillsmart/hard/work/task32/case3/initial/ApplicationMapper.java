package ru.skubatko.dev.skillsmart.hard.work.task32.case3.initial;

import ru.skubatko.dev.skillsmart.hard.work.task32.case3.common.ApplicationEntity;
import ru.skubatko.dev.skillsmart.hard.work.task32.case3.common.ApplicationOutputDTO;

public class ApplicationMapper {

    public ApplicationOutputDTO toDTOOutput(ApplicationEntity applicationEntity) {
        ApplicationOutputDTO applicationOutputDTO = new ApplicationOutputDTO();

        applicationOutputDTO.setApplicationId(applicationEntity.getApplicationId());
        applicationOutputDTO.setAppDate(applicationEntity.getAppDate());
        applicationOutputDTO.setAppLastUpDate(applicationEntity.getAppLastUpDate());

        return applicationOutputDTO;
    }
}
