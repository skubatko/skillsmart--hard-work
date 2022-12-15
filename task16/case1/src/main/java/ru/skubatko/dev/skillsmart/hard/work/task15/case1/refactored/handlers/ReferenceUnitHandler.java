package ru.skubatko.dev.skillsmart.hard.work.task15.case1.refactored.handlers;

import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.ReferenceItemEntity;
import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.ReferenceUnit;

public interface ReferenceUnitHandler {

    void handle(ReferenceUnit referenceUnit, String xEmployeePIN, ReferenceItemEntity referenceItem);

    boolean isApplicable(ReferenceUnit referenceUnit);
}
