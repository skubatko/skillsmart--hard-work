package ru.skubatko.dev.skillsmart.hard.work.task35.case3;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class ReportFileProcessDto {
    Predicate predicate;
    Integer reportId;
    UUID reportFileId;
    LocalDateTime reportCreatedDttm;
}
