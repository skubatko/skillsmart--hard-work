package ru.skubatko.dev.skillsmart.hard.work.task15.case1.refactored.handlers;

import static com.sun.java.accessibility.util.EventID.ITEM;
import static ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.ReferenceUnit.YEAR_OF_MANUFACTURE;

import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.ReferenceItemEntity;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.ReferenceUnit;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleSetYearEntity;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleSetYearRepository;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleSetYearService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class YearOfManufactureReferenceUnitHandler implements ReferenceUnitHandler {
    private final VehicleSetYearRepository vehicleSetYearRepository;
    private final VehicleSetYearService vehicleSetYearService;

    @Override
    public void handle(ReferenceUnit referenceUnit, String xEmployeePIN, ReferenceItemEntity referenceItem) {
        if (!isApplicable(referenceUnit)) {
            return;
        }

        List<VehicleSetYearEntity> allReferencingSetYears = vehicleSetYearRepository.findAllByYear_IdAndArchiveIsFalse(referenceItem.getId());
        if (!allReferencingSetYears.isEmpty()) {
            vehicleSetYearService.deleteAll(xEmployeePIN, allReferencingSetYears, ITEM);
        }
    }

    @Override
    public boolean isApplicable(ReferenceUnit referenceUnit) {
        return referenceUnit == YEAR_OF_MANUFACTURE;
    }
}
