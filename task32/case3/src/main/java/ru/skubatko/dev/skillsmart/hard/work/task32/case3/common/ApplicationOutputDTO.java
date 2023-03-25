package ru.skubatko.dev.skillsmart.hard.work.task32.case3.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ApplicationOutputDTO {
    private Long applicationId;
    private LocalDateTime appDate;
    private LocalDateTime appLastUpDate;
}
