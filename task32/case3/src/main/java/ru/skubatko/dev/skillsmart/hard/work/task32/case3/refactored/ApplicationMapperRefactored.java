package ru.skubatko.dev.skillsmart.hard.work.task32.case3.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task32.case3.common.ApplicationEntity;
import ru.skubatko.dev.skillsmart.hard.work.task32.case3.common.ApplicationOutputDTO;

import java.time.LocalDateTime;

public class ApplicationMapperRefactored {

    public ApplicationOutputDTO toDTOOutput(ApplicationEntity applicationEntity) {
        if (applicationEntity == null) {
            return null;
        }

        return new ApplicationOutputDTO()
            .setApplicationId(getApplicationId(applicationEntity))
            .setAppDate(getAppDate(applicationEntity))
            .setAppLastUpDate(getAppLastUpDate(applicationEntity));
    }

    private Long getApplicationId(ApplicationEntity applicationEntity) {
        return applicationEntity.getApplicationId();
    }

    private LocalDateTime getAppDate(ApplicationEntity applicationEntity) {
        return applicationEntity.getAppDate();
    }

    private LocalDateTime getAppLastUpDate(ApplicationEntity applicationEntity) {
        return applicationEntity.getAppLastUpDate();
    }
}
