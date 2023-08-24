package ru.skubatko.dev.skillsmart.hard.work.task65.case3.rest;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ApplicantCreateRequestTO {
    String name;
}
