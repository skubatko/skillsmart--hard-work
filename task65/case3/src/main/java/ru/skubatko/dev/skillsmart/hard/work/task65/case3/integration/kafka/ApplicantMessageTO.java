package ru.skubatko.dev.skillsmart.hard.work.task65.case3.integration.kafka;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class ApplicantMessageTO {
    Long id;
    String name;
    LocalDateTime createdAt;
}
