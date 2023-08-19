package ru.skubatko.dev.skillsmart.hard.work.task64.case3.initial;

import java.util.Optional;

public class ApplicationService {

    public void setCarPurchasingEntity(ApplicationInputDTO applicationInputDTO, ApplicationEntity application) {
        Optional.ofNullable(applicationInputDTO.getBuyCar())
            .ifPresent(purchase -> {
                CarPurchasingEntity carPurchasing = application.getCarPurchasingEntity();
                if (carPurchasing == null) {
                    carPurchasing = new CarPurchasingEntity();
                    carPurchasing.setApplicationEntity(application);
                }
                carPurchasing.setAdvtId(applicationInputDTO.getBuyCar().getAdvtId());
                application.setCarPurchasingEntity(carPurchasingRepository.save(carPurchasing));
            });
    }
}
