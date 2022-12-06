package ru.skubatko.dev.skillsmart.hard.work.task13.case2.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task13.case2.common.MessageService;
import ru.skubatko.dev.skillsmart.hard.work.task13.case2.common.ReferenceItemRepository;

import lombok.NonNull;
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
        @NonNull String xEmployeePIN,
        @NonNull Long referenceId,
        @NonNull String referenceSysName,
        @NonNull Boolean archive,
        @NonNull String code,
        @NonNull String brief
    ) {
        List<Object> predicates = new ArrayList<>();
        predicates.add(referenceId);
        predicates.add(referenceSysName);
        predicates.add(code);
        predicates.add(archive);
        predicates.add(brief);

        List<Object> items;
        items = getObjects(predicates, referenceItemRepository);
        return new ArrayList<>(items);
    }

    private static List<Object> getObjects(List<Object> predicates, ReferenceItemRepository referenceItemRepository) {
        List<Object> items;
        Object fullPredicate = predicates.get(0);
        int index = 0;
        for (Object ignored : predicates) {
            if (index < predicates.size() - 1) {
                index++;
                fullPredicate = predicates.get(index);
            }
        }
        items = referenceItemRepository.findAll(fullPredicate);
        return items;
    }
}
