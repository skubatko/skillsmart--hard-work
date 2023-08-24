package ru.skubatko.dev.skillsmart.hard.work.task65.case2.web.rest;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class CarRequestTO {
    Long id;
    UUID personId;
    String name;
    String country;
    LocalDateTime createdAt;
}
