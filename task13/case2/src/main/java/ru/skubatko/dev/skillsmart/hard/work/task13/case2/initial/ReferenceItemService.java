package ru.skubatko.dev.skillsmart.hard.work.task13.case2.initial;

import ru.skubatko.dev.skillsmart.hard.work.task13.case2.common.BusinessException;
import ru.skubatko.dev.skillsmart.hard.work.task13.case2.common.Errors;
import ru.skubatko.dev.skillsmart.hard.work.task13.case2.common.MessageService;
import ru.skubatko.dev.skillsmart.hard.work.task13.case2.common.ReferenceItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ReferenceItemService {
    private final MessageService messageService;
    private final ReferenceItemRepository referenceItemRepository;

    public List<Object> findAll(
        String xEmployeePIN,
        Long referenceId,
        String referenceSysName,
        Boolean archive,
        String code,
        String brief
    ) {

        if (referenceId == null && referenceSysName == null) {
            throw new BusinessException(
                Errors.REFERENCE_ID_AND_REFERENCE_SYSNAME_IS_NULL,
                messageService.getLocalizedMessage("messages.one_of_required_param_referenceid_or_refsysname_shouldnt_be_null")
            );
        }

        List<Object> predicates = new ArrayList<>();

        if (referenceId != null) {
            predicates.add(referenceId);
        }
        if (referenceSysName != null && !referenceSysName.isEmpty()) {
            predicates.add(referenceSysName);
        }
        if (code != null && !code.isEmpty()) {
            predicates.add(code);
        }
        if (archive != null) {
            predicates.add(archive);
        }
        if (brief != null && !brief.isEmpty()) {
            predicates.add(brief);
        }

        List<Object> items;
        if (predicates.isEmpty()) {
            items = referenceItemRepository.findAll();
        } else if (predicates.size() == 1) {
            items = referenceItemRepository.findAll(predicates.get(0));
        } else {
            Object fullPredicate = predicates.get(0);
            int index = 0;
            for (Object ignored : predicates) {
                if (index < predicates.size() - 1) {
                    index++;
                    fullPredicate = predicates.get(index);
                }
            }
            items = referenceItemRepository.findAll(fullPredicate);
        }

        List<Object> itemsDTO = new ArrayList<>();

        for (Object item : items) {
            itemsDTO.add(item);
        }

        return itemsDTO;
    }
}
