package ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored;

import lombok.NonNull;
import lombok.Value;

@Value
public class ReferenceItemDto {
    @NonNull String xEmployeePIN;
    @NonNull Long referenceId;
    @NonNull String referenceSysName;
    @NonNull Boolean archive;
    @NonNull String code;
    @NonNull String brief;
}
