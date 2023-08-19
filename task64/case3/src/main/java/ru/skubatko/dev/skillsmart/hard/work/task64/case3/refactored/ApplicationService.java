package ru.skubatko.dev.skillsmart.hard.work.task64.case3.refactored;

import java.util.Optional;

public class ApplicationService {

    public ApplicationDTO setCarPurchasing(ApplicationInputDTO applicationInputDTO, ApplicationDTO entity) {
        ApplicationDTO application = entity.deepCopy();
        Optional.ofNullable(applicationInputDTO.getBuyCar())
            .ifPresent(purchase -> {
                CarPurchasingEntity carPurchasing = application.getCarPurchasing();
                if (carPurchasing == null) {
                    carPurchasing = new CarPurchasingEntity();
                    carPurchasing.setApplicationEntity(application);
                }
                carPurchasing.setAdvtId(applicationInputDTO.getBuyCar().getAdvtId());
                application.setCarPurchasing(carPurchasingRepository.save(carPurchasing));
            });
        return application;
    }
}
