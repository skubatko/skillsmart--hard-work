package ru.skubatko.dev.skillsmart.hard.work.task18;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GhostStateCases {

    private ReferenceItemEntity checkForModify(Long id, ReferenceItemDTO referenceItemDTO) {
        Optional<ReferenceItemEntity> byId = referenceItemRepository.findById(id);

        if (byId.isEmpty()) {
            throw new BusinessException(
                Errors.REFERENCE_ITEM_NOT_FOUND_BY_ID,
                messageService.getLocalizedMessage("messages.reference_item_not_found_by_id") + " " + id
            );
        }

        ReferenceItemEntity referenceItemEntity = byId.get();
    }


    private void checkForMass(List<ReferenceItemDTO> referenceItemsDTO) {

        if (referenceItemsDTO == null) {
            throw new BusinessException(
                Errors.REFERENCE_ITEM_LIST_IS_EMPTY,
                messageService.getLocalizedMessage("messages.reference_item_list_is_empty")
            );
        }

        //Проверить весь входной список на уникальные коды
        for (ReferenceItemDTO referenceItemDTO : referenceItemsDTO) {

            Integer count = 0;
            Long curReferenceId = referenceItemDTO.getReferenceId();
            String curCode = referenceItemDTO.getCode();

            for (int i = 0; i < referenceItemsDTO.size(); i++) {
                ReferenceItemDTO checkReferenceItemDTO = referenceItemsDTO.get(i);
                if (checkReferenceItemDTO.getReferenceId() != null && checkReferenceItemDTO.getCode() != null) {
                    if (checkReferenceItemDTO.getReferenceId().equals(curReferenceId) && checkReferenceItemDTO.getCode().equalsIgnoreCase(curCode)) {
                        count++;
                    }
                }
            }

            if (count > 1) {
                throw new BusinessException(
                    Errors.REFERENCE_ITEM_DUPLICATED_REFERENCEID_AND_CODE,
                    messageService.getLocalizedMessage("messages.reference_item_list_has_not_unique_referenceid") + " " +
                        curReferenceId + " " +
                        messageService.getLocalizedMessage("messages.and_code") + " " +
                        curCode
                );
            }
        }
    }


    private ReferenceItemEntity processLinkedEntities(Long id, ReferenceItemDTO referenceItemDTO) {

        Long itemId = null;

        if (id != null) {
            itemId = id;
        } else if (referenceItemDTO.getId() != null) {
            itemId = referenceItemDTO.getId();
        }

        //проверка ролей
        List<RoleDTO> roleDTOList = referenceItemDTO.getRoles();

        List<RoleEntity> roles = new ArrayList<>();
        if (roleDTOList != null) {
            for (RoleDTO roleDTO : roleDTOList) {
                Optional<RoleEntity> optional = roleRepository.findBySysnameEquals(roleDTO.getSysname());
                if (optional.isPresent()) {
                    RoleEntity role = optional.get();
                    roles.add(role);
                } else {
                    throw new BusinessException(
                        Errors.ROLE_NOT_FOUND_BY_ID,
                        messageService.getLocalizedMessage("messages.role_not_found_by_id") + " " + roleDTO.getId()
                    );
                }
            }
        }

    }
}
