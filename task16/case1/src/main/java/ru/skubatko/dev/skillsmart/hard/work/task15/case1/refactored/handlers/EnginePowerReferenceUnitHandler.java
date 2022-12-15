package ru.skubatko.dev.skillsmart.hard.work.task15.case1.refactored.handlers;

import static ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.ReferenceUnit.ENGINE_POWER;

import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.ReferenceItemEntity;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.ReferenceUnit;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleSetEntity;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleSetRepository;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleSetService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class EnginePowerReferenceUnitHandler implements ReferenceUnitHandler {
    private final VehicleSetRepository vehicleSetRepository;
    private final VehicleSetService vehicleSetService;

    @Override
    public void handle(ReferenceUnit referenceUnit, String xEmployeePIN, ReferenceItemEntity referenceItem) {
        if (!isApplicable(referenceUnit)) {
            return;
        }

        List<VehicleSetEntity> allPowerSets = vehicleSetRepository.findAllByPower_IdAndArchiveIsFalse(referenceItem.getId());
        if (!allPowerSets.isEmpty()) {
            vehicleSetService.deleteAll(xEmployeePIN, allPowerSets, referenceUnit);
        }
    }

    @Override
    public boolean isApplicable(ReferenceUnit referenceUnit) {
        return referenceUnit == ENGINE_POWER;
    }
}
