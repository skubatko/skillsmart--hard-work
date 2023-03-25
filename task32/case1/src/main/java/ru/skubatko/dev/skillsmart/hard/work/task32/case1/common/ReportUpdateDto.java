package ru.skubatko.dev.skillsmart.hard.work.task32.case1.common;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReportUpdateDto {
    Status status;
    String errorText;
}
