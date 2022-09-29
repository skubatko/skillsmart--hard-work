package ru.skubatko.dev.skillsmart.hard.work.task05.case2;

import static java.util.Objects.nonNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис управления моделями автомобилей обеспечивает возможность упорядоченного хранения информации о моделях
 * автомобилей гаража. Данный сервис является неотъемлемой частью функционирования гаража и обеспечивает релевантной
 * информацией другие сервисы гаража.
 */
@RequiredArgsConstructor
public class VehicleModelService {
    private final VehicleModelRepository vehicleModelRepository;
    private final VehicleBrandRepository vehicleBrandRepository;
    private final ValidationService validationService;
    private final ReferenceItemService referenceItemService;
    private final MessageService messageService;

    public VehicleModelEntity checkAndPrepareVehicleModel(Long id, VehicleModelReqDTO vehicleModelReqDTO) {

        validationService.validateDTO(vehicleModelReqDTO);

        if (id != null) {
            VehicleModelEntity vehicleModelEntity = vehicleModelRepository.findById(id)
                .orElseThrow(() -> new BusinessException(Errors.VEHICLE_MODEL_NOT_FOUND_BY_ID,
                    messageService.getLocalizedMessage("messages.vehicle_model_not_found_by_id") + " " + id
                ));

            if (!vehicleModelEntity.getName().equalsIgnoreCase(vehicleModelReqDTO.getName())) {
                checkName(vehicleModelReqDTO);
            }
        } else {
            checkName(vehicleModelReqDTO);
        }

        VehicleBrandEntity brand = null;
        ReferenceItemEntity kind = null;
        List<ReferenceItemEntity> ownForms = new ArrayList<>();

        Long brandId = null;

        if (vehicleModelReqDTO.getBrand() != null) {
            brandId = vehicleModelReqDTO.getBrand().getId();
        }

        if (brandId == null) {
            throw new BusinessException(
                Errors.VEHICLE_BRAND_ID_IS_NULL,
                messageService.getLocalizedMessage("messages.missing_required_param_vehicle_brand_id")
            );
        }

        Long kindId = null;

        if (vehicleModelReqDTO.getKind() != null) {
            kindId = vehicleModelReqDTO.getKind();
        }

        if (kindId == null) {
            throw new BusinessException(
                Errors.VEHICLE_MODEL_KIND_ID_IS_NULL,
                messageService.getLocalizedMessage("messages.missing_required_param_vehicle_model_kind_id")
            );
        }

        if (brandId != null) {
            Optional<VehicleBrandEntity> foundBrand = vehicleBrandRepository.findById(brandId);
            if (foundBrand.isPresent()) {
                brand = foundBrand.get();
            } else {
                throw new BusinessException(
                    Errors.VEHICLE_BRAND_NOT_FOUND_BY_ID,
                    messageService.getLocalizedMessage("messages.vehicle_brand_not_found_by_id") + " " + brandId
                );
            }
        }

        if (kindId != null) {
            kind = referenceItemService.checkAndPrepareReferenceItem(
                kindId,
                "vehicleKind",
                Errors.VEHICLE_KIND_NOT_FOUND_BY_ID,
                messageService.getLocalizedMessage("messages.vehicle_kind_not_found_by_id"),
                Errors.INVALID_VEHICLE_KIND_REFERENCE,
                messageService.getLocalizedMessage("messages.invalid_vehicle_kind_reference")
            );
        }

        if (vehicleModelReqDTO.getOwnForms() != null && !vehicleModelReqDTO.getOwnForms().isEmpty()) {

            List<RefItemReqIdDTO> items = vehicleModelReqDTO.getOwnForms();
            for (RefItemReqIdDTO item : items) {
                if (item.getId() != null) {
                    ReferenceItemEntity ownForm = referenceItemService.checkAndPrepareReferenceItem(
                        item.getId(),
                        "propertyForm",
                        Errors.PROPERTY_FORM_NOT_FOUND_BY_ID,
                        messageService.getLocalizedMessage("messages.property_form_not_found_by_id"),
                        Errors.INVALID_PROPERTY_FORM_REFERENCE,
                        messageService.getLocalizedMessage("messages.invalid_property_form_reference")
                    );
                    ownForms.add(ownForm);
                }
            }

        }

        return new VehicleModelEntity()
            .setId(nonNull(id) ? id : null)
            .setBrand(brand)
            .setName(vehicleModelReqDTO.getName())
            .setKind(kind)
            .setCreditedAsNew(vehicleModelReqDTO.getCreditedAsNew())
            .setCreditedAsUsed(vehicleModelReqDTO.getCreditedAsUsed())
            .setOwnForms(ownForms)
            .setArchive(false);
    }

    private void checkName(VehicleModelReqDTO vehicleModelReqDTO) {
    }

    @Data
    @Accessors(chain = true)
    private static class VehicleModelEntity {
        private Long id;
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
        private Long kind;
        private List<RefItemReqIdDTO> ownForms;
        private String creditedAsNew;
        private String creditedAsUsed;
    }

    private static class ValidationService {
        public void validateDTO(VehicleModelReqDTO vehicleModelReqDTO) {}
    }

    private static class VehicleModelRepository {
        public Optional<VehicleModelEntity> findById(Long id) {
            return null;
        }
    }


    @Data
    private static class VehicleBrandEntity {
        private Long id;
    }

    private static class ReferenceItemEntity {}

    private static class VehicleBrandRepository {
        public Optional<VehicleBrandEntity> findById(Long brandId) {
            return null;
        }
    }

    private static class ReferenceItemService {
        public ReferenceItemEntity checkAndPrepareReferenceItem(
            Long kindId,
            String vehicleKind,
            String vehicleKindNotFoundById,
            String localizedMessage,
            String invalidVehicleKindReference,
            String localizedMessage1
        ) {
            return null;
        }
    }

    @Data
    private static class RefItemReqIdDTO {
        private Long id;
    }
}
