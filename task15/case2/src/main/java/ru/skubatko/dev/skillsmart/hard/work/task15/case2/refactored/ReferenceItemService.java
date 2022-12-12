package ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task15.case2.common.MessageService;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.common.ReferenceItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ReferenceItemService {
    private final MessageService messageService;
    private final ReferenceItemRepository referenceItemRepository;

    public List<Object> findAll(ReferenceItemDto dto) {
        List<Object> predicates = new ArrayList<>();
        predicates.add(dto.getReferenceId());
        predicates.add(dto.getReferenceSysName());
        predicates.add(dto.getCode());
        predicates.add(dto.getArchive());
        predicates.add(dto.getBrief());

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
