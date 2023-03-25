package ru.skubatko.dev.skillsmart.hard.work.task32.case1.common;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Predicate;

@Value
@Builder
public class ReportFileProcessDto {
    Predicate predicate;
    Integer reportId;
    UUID reportFileId;
    LocalDateTime reportCreatedDttm;
}
