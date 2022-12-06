package ru.skubatko.dev.skillsmart.hard.work.task13.case1.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.ReferenceItemEntity;
import ru.skubatko.dev.skillsmart.hard.work.task13.case1.common.ReferenceUnit;
import ru.skubatko.dev.skillsmart.hard.work.task13.case1.refactored.handlers.ReferenceUnitHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ReferenceItemService {
    private final List<ReferenceUnitHandler> handlers;

    private void removeBindings(String xEmployeePIN, ReferenceItemEntity referenceItem) {
        Optional<ReferenceUnit> setParticipatingUnit = getReferenceUnit(referenceItem);
        if (setParticipatingUnit.isPresent()) {
            handlers.forEach(handler -> handler.handle(setParticipatingUnit.get(), xEmployeePIN, referenceItem));
        }
    }

    private Optional<ReferenceUnit> getReferenceUnit(ReferenceItemEntity referenceItem) {
        return null;
    }
}
