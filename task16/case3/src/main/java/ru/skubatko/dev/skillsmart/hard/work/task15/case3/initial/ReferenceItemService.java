package ru.skubatko.dev.skillsmart.hard.work.task15.case3.initial;

import ru.skubatko.dev.skillsmart.hard.work.task15.case3.common.FilteredRefItemReqDTO;
import ru.skubatko.dev.skillsmart.hard.work.task15.case3.common.ReferenceItemEntity;
import ru.skubatko.dev.skillsmart.hard.work.task15.case3.common.ReferenceItemRepository;
import ru.skubatko.dev.skillsmart.hard.work.task15.case3.common.RoleEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ReferenceItemService {
    private final ReferenceItemRepository referenceItemRepository;

    public List<FilteredRefItemResDTO> getFilteredItems(String xEmployeePIN,
        FilteredRefItemReqDTO filteredRefItemReqDTO,
        Boolean archive) {

        List<FilteredRefItemResDTO> filteredItems = new ArrayList<>();

        //получаем элементы справочника
        List<ReferenceItemEntity> items = referenceItemRepository.findByReferenceIdEquals(archive);

        for (ReferenceItemEntity item : items) {

            List<RoleEntity> curItemRoles = item.getRoles();

            if (curItemRoles == null) {
                curItemRoles = new ArrayList<>();
            }

            //используем уникальные роли с групп и справочников
            if (curItemRoles.size() == 0 && xEmployeePIN != null) {
                FilteredRefItemResDTO filteredRefItemResDTO = new FilteredRefItemResDTO();
                filteredRefItemResDTO.setId(item.getId());
                filteredRefItemResDTO.setReferenceId(item.getReference().getId());
                filteredRefItemResDTO.setCode(item.getCode());
                filteredRefItemResDTO.setBrief(item.getBrief());
                filteredRefItemResDTO.setName(item.getName());
                filteredRefItemResDTO.setDescription(item.getDescription());
                filteredItems.add(filteredRefItemResDTO);
            }
            //используем роли с текущего элемента
            else if (curItemRoles.size() != 0) {
                FilteredRefItemResDTO filteredRefItemResDTO = new FilteredRefItemResDTO();
                filteredRefItemResDTO.setId(item.getId());
                filteredRefItemResDTO.setReferenceId(item.getReference().getId());
                filteredRefItemResDTO.setCode(item.getCode());
                filteredRefItemResDTO.setBrief(item.getBrief());
                filteredRefItemResDTO.setName(item.getName());
                filteredRefItemResDTO.setDescription(item.getDescription());
                filteredItems.add(filteredRefItemResDTO);
            }
            //нет связи с ролями - доступен всем
            else {
                FilteredRefItemResDTO filteredRefItemResDTO = new FilteredRefItemResDTO();
                filteredRefItemResDTO.setId(item.getId());
                filteredRefItemResDTO.setReferenceId(item.getReference().getId());
                filteredRefItemResDTO.setCode(item.getCode());
                filteredRefItemResDTO.setBrief(item.getBrief());
                filteredRefItemResDTO.setName(item.getName());
                filteredRefItemResDTO.setDescription(item.getDescription());
                filteredItems.add(filteredRefItemResDTO);
            }
        }

        log.debug("getFilteredItems() - end: filteredItems size = {}", filteredItems.size());
        return filteredItems;
    }
}
