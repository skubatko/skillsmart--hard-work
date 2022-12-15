package ru.skubatko.dev.skillsmart.hard.work.task15.case3.refactored;

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
        List<ReferenceItemEntity> items = referenceItemRepository.findByReferenceIdEquals(archive);
        for (ReferenceItemEntity item : items) {
            List<RoleEntity> curItemRoles = item.getRoles();
            //используем уникальные роли с групп и справочников
            if (curItemRoles.size() == 0 && xEmployeePIN != null) {
                filteredItems.add(FilteredRefItemResDTO.fromReferenceItemEntity(item));
            }
            //используем роли с текущего элемента
            else if (curItemRoles.size() != 0) {
                filteredItems.add(FilteredRefItemResDTO.fromReferenceItemEntity(item));
            }
            //нет связи с ролями - доступен всем
            else {
                filteredItems.add(FilteredRefItemResDTO.fromReferenceItemEntity(item));
            }
        }
        log.debug("getFilteredItems() - end: filteredItems size = {}", filteredItems.size());
        return filteredItems;
    }
}
