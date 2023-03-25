package ru.skubatko.dev.skillsmart.hard.work.task32.case3.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationEntity {
    private Long applicationId;
    private String number;
    private String statusCode;
    private LocalDateTime appDate;
    private LocalDateTime appLastUpDate;
}
