package ru.skubatko.dev.skillsmart.hard.work.task13.case1.refactored.handlers;

import static com.sun.java.accessibility.util.EventID.ITEM;
import static ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.ReferenceUnit.PRODUCTION_KIND;

import ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.ReferenceItemEntity;
import ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.ReferenceUnit;
import ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.VehicleBrandEntity;
import ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.VehicleBrandRepository;
import ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.VehicleBrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ProductionKindReferenceUnitHandler implements ReferenceUnitHandler {
    private final VehicleBrandRepository vehicleBrandRepository;
    private final VehicleBrandService vehicleBrandService;

    @Override
    public void handle(ReferenceUnit referenceUnit, String xEmployeePIN, ReferenceItemEntity referenceItem) {
        if (!isApplicable(referenceUnit)) {
            return;
        }

        List<VehicleBrandEntity> allReferencingBrands = vehicleBrandRepository.findAllByProductionKind_IdAndArchiveIsFalse(referenceItem.getId());
        if (!allReferencingBrands.isEmpty()) {
            vehicleBrandService.deleteAll(xEmployeePIN, allReferencingBrands, ITEM);
        }
    }

    @Override
    public boolean isApplicable(ReferenceUnit referenceUnit) {
        return referenceUnit == PRODUCTION_KIND;
    }
}
