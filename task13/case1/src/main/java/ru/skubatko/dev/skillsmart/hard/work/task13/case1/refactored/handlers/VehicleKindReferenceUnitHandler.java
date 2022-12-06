package ru.skubatko.dev.skillsmart.hard.work.task13.case1.refactored.handlers;

import static com.sun.java.accessibility.util.EventID.ITEM;
import static ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.ReferenceUnit.VEHICLE_KIND;

import ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.ReferenceItemEntity;
import ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.ReferenceUnit;
import ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.VehicleModelEntity;
import ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.VehicleModelRepository;
import ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.VehicleModelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class VehicleKindReferenceUnitHandler implements ReferenceUnitHandler {
    private final VehicleModelRepository vehicleModelRepository;
    private final VehicleModelService vehicleModelService;

    @Override
    public void handle(ReferenceUnit referenceUnit, String xEmployeePIN, ReferenceItemEntity referenceItem) {
        if (!isApplicable(referenceUnit)) {
            return;
        }

        List<VehicleModelEntity> allReferencingModels = vehicleModelRepository.findAllByKind_IdAndArchiveIsFalse(referenceItem.getId());
        if (!allReferencingModels.isEmpty()) {
            vehicleModelService.deleteAll(xEmployeePIN, allReferencingModels, ITEM);
        }
    }

    @Override
    public boolean isApplicable(ReferenceUnit referenceUnit) {
        return referenceUnit == VEHICLE_KIND;
    }
}
