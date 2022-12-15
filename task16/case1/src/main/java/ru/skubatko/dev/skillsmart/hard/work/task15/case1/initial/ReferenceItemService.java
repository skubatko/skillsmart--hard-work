package ru.skubatko.dev.skillsmart.hard.work.task15.case1.initial;

import static com.sun.java.accessibility.util.EventID.ITEM;

import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.ReferenceItemEntity;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.ReferenceUnit;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleBrandEntity;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleBrandRepository;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleBrandService;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleModelEntity;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleModelRepository;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleModelService;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleSetEntity;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleSetRepository;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleSetService;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleSetYearEntity;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleSetYearRepository;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.VehicleSetYearService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ReferenceItemService {
    private final VehicleBrandRepository vehicleBrandRepository;
    private final VehicleModelRepository vehicleModelRepository;
    private final VehicleSetRepository vehicleSetRepository;
    private final VehicleSetYearRepository vehicleSetYearRepository;
    private final VehicleSetYearService vehicleSetYearService;
    private final VehicleSetService vehicleSetService;
    private final VehicleModelService vehicleModelService;
    private final VehicleBrandService vehicleBrandService;

    private void removeBindings(String xEmployeePIN, ReferenceItemEntity referenceItem) {
        Optional<ReferenceUnit> setParticipatingUnit = getReferenceUnit(referenceItem);
        if (setParticipatingUnit.isPresent()) {
            switch (setParticipatingUnit.get()) {
                case PRODUCTION_KIND:
                    List<VehicleBrandEntity> allReferencingBrands = vehicleBrandRepository.findAllByProductionKind_IdAndArchiveIsFalse(referenceItem.getId());
                    if (!allReferencingBrands.isEmpty()) {
                        vehicleBrandService.deleteAll(xEmployeePIN, allReferencingBrands, ITEM);
                    }
                    break;
                case VEHICLE_KIND:
                    List<VehicleModelEntity> allReferencingModels = vehicleModelRepository.findAllByKind_IdAndArchiveIsFalse(referenceItem.getId());
                    if (!allReferencingModels.isEmpty()) {
                        vehicleModelService.deleteAll(xEmployeePIN, allReferencingModels, ITEM);
                    }
                    break;
                case BODY_TYPE:
                    List<VehicleSetEntity> allBodySets = vehicleSetRepository.findAllByBody_IdAndArchiveIsFalse(referenceItem.getId());
                    if (!allBodySets.isEmpty()) {
                        vehicleSetService.deleteAll(xEmployeePIN, allBodySets, setParticipatingUnit.get());
                    }
                    break;
                case ENGINE_TYPE:
                    List<VehicleSetEntity> allEngineTypeSets = vehicleSetRepository.findAllByEngineType_IdAndArchiveIsFalse(referenceItem.getId());
                    if (!allEngineTypeSets.isEmpty()) {
                        vehicleSetService.deleteAll(xEmployeePIN, allEngineTypeSets, setParticipatingUnit.get());
                    }
                    break;
                case ENGINE_POWER:
                    List<VehicleSetEntity> allPowerSets = vehicleSetRepository.findAllByPower_IdAndArchiveIsFalse(referenceItem.getId());
                    if (!allPowerSets.isEmpty()) {
                        vehicleSetService.deleteAll(xEmployeePIN, allPowerSets, setParticipatingUnit.get());
                    }
                    break;
                case ENGINE_CAPACITY:
                    List<VehicleSetEntity> allEngineSizeSets = vehicleSetRepository.findAllByEngineSize_IdAndArchiveIsFalse(referenceItem.getId());
                    if (!allEngineSizeSets.isEmpty()) {
                        vehicleSetService.deleteAll(xEmployeePIN, allEngineSizeSets, setParticipatingUnit.get());
                    }
                    break;
                case TRANSMISSION:
                    List<VehicleSetEntity> allTransmissionSets = vehicleSetRepository.findAllByTransmission_IdAndArchiveIsFalse(referenceItem.getId());
                    if (!allTransmissionSets.isEmpty()) {
                        vehicleSetService.deleteAll(xEmployeePIN, allTransmissionSets, setParticipatingUnit.get());
                    }
                    break;
                case YEAR_OF_MANUFACTURE:
                    List<VehicleSetYearEntity> allReferencingSetYears = vehicleSetYearRepository.findAllByYear_IdAndArchiveIsFalse(referenceItem.getId());
                    if (!allReferencingSetYears.isEmpty()) {
                        vehicleSetYearService.deleteAll(xEmployeePIN, allReferencingSetYears, ITEM);
                    }
                    break;
            }
        }
    }

    private Optional<ReferenceUnit> getReferenceUnit(ReferenceItemEntity referenceItem) {
        return null;
    }
}
