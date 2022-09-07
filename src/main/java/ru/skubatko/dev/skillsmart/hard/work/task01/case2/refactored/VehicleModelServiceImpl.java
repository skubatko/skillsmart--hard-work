package ru.skubatko.dev.skillsmart.hard.work.task01.case2.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task01.case2.dependency.BusinessException;
import ru.skubatko.dev.skillsmart.hard.work.task01.case2.dependency.Errors;
import ru.skubatko.dev.skillsmart.hard.work.task01.case2.dependency.MessageService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class VehicleModelServiceImpl {
    private final VehicleBrandRepository vehicleBrandRepository;
    private final ValidationService validationService;
    private final ReferenceItemService referenceItemService;
    private final MessageService messageService;

    public VehicleModelEntity checkAndPrepareVehicleModel(Id id, VehicleModelReqDTO vehicleModelReqDTO) {
        validationService.validate(id, vehicleModelReqDTO);

        Id brandId = vehicleModelReqDTO.getBrand().getId();
        VehicleBrandEntity brand = vehicleBrandRepository.findById(brandId)
            .orElseThrow(() ->
                new BusinessException(
                    Errors.VEHICLE_BRAND_NOT_FOUND_BY_ID,
                    messageService.getLocalizedMessage("messages.vehicle_brand_not_found_by_id") + " " + brandId));

        ReferenceItemEntity kind = referenceItemService.checkAndPrepareReferenceItem(
            vehicleModelReqDTO.getKindId(),
            "vehicleKind",
            Errors.VEHICLE_KIND_NOT_FOUND_BY_ID,
            messageService.getLocalizedMessage("messages.vehicle_kind_not_found_by_id"),
            Errors.INVALID_VEHICLE_KIND_REFERENCE,
            messageService.getLocalizedMessage("messages.invalid_vehicle_kind_reference"));

        List<ReferenceItemEntity> ownForms = vehicleModelReqDTO.getOwnForms().stream()
            .map(dto -> referenceItemService.checkAndPrepareReferenceItem(
                dto.getId(),
                "propertyForm",
                Errors.PROPERTY_FORM_NOT_FOUND_BY_ID,
                messageService.getLocalizedMessage("messages.property_form_not_found_by_id"),
                Errors.INVALID_PROPERTY_FORM_REFERENCE,
                messageService.getLocalizedMessage("messages.invalid_property_form_reference")))
            .collect(Collectors.toList());

        return new VehicleModelEntity()
            .setId(id)
            .setBrand(brand)
            .setName(vehicleModelReqDTO.getName())
            .setKind(kind)
            .setCreditedAsNew(vehicleModelReqDTO.getCreditedAsNew())
            .setCreditedAsUsed(vehicleModelReqDTO.getCreditedAsUsed())
            .setOwnForms(ownForms)
            .setArchive(false);
    }

    @Data
    private static class Id {
        private Long id;

        public static final Id NONE = new Id();
    }

    @Data
    @Accessors(chain = true)
    private static class VehicleModelEntity {
        private Id id = Id.NONE;
        private VehicleBrandEntity brand;
        private String name;
        private ReferenceItemEntity kind;
        private String creditedAsNew;
        private String creditedAsUsed;
        private List<ReferenceItemEntity> ownForms;
        private Boolean archive;
    }

    @Data
    private static class VehicleModelReqDTO {
        private String name;
        private VehicleBrandEntity brand;
        private Id kindId;
        private List<RefItemReqIdDTO> ownForms = Collections.emptyList();
        private String creditedAsNew;
        private String creditedAsUsed;
    }

    @RequiredArgsConstructor
    private static class ValidationService {
        private final VehicleModelRepository vehicleModelRepository;
        private final MessageService messageService;

        public void validate(Id id, VehicleModelReqDTO vehicleModelReqDTO) {
            validateName(id, vehicleModelReqDTO);

            if (Objects.equals(vehicleModelReqDTO.getBrand().getId(), Id.NONE)) {
                throw new BusinessException(
                    Errors.VEHICLE_BRAND_ID_IS_NULL,
                    messageService.getLocalizedMessage("messages.missing_required_param_vehicle_brand_id")
                );
            }

            if (Objects.equals(vehicleModelReqDTO.getKindId(), Id.NONE)) {
                throw new BusinessException(
                    Errors.VEHICLE_MODEL_KIND_ID_IS_NULL,
                    messageService.getLocalizedMessage("messages.missing_required_param_vehicle_model_kind_id")
                );
            }
        }

        private void validateName(Id id, VehicleModelReqDTO vehicleModelReqDTO) {
            if (!(Objects.equals(id, Id.NONE))) {
                validateName(vehicleModelReqDTO);
            }

            VehicleModelEntity vehicleModelEntity = vehicleModelRepository.findById(id)
                .orElseThrow(() -> new BusinessException(Errors.VEHICLE_MODEL_NOT_FOUND_BY_ID,
                    messageService.getLocalizedMessage("messages.vehicle_model_not_found_by_id") + " " + id
                ));

            if (!(vehicleModelEntity.getName().equalsIgnoreCase(vehicleModelReqDTO.getName()))) {
                validateName(vehicleModelReqDTO);
            }
        }

        private void validateName(VehicleModelReqDTO vehicleModelReqDTO) {
        }
    }

    private static class VehicleModelRepository {
        public Optional<VehicleModelEntity> findById(Id id) {
            return Optional.empty();
        }
    }


    @Data
    private static class VehicleBrandEntity {
        private Id id = Id.NONE;

        public static final VehicleBrandEntity NONE = new VehicleBrandEntity();
    }

    private static class ReferenceItemEntity {
        public static final ReferenceItemEntity NONE = new ReferenceItemEntity();
    }

    private static class VehicleBrandRepository {
        public Optional<VehicleBrandEntity> findById(Id brandId) {
            return Optional.empty();
        }
    }

    private static class ReferenceItemService {
        public ReferenceItemEntity checkAndPrepareReferenceItem(
            Id kindId,
            String vehicleKind,
            String vehicleKindNotFoundById,
            String localizedMessage,
            String invalidVehicleKindReference,
            String localizedMessage1
        ) {
            return ReferenceItemEntity.NONE;
        }
    }

    @Data
    private static class RefItemReqIdDTO {
        private Id id = Id.NONE;
    }
}
