package ru.skubatko.dev.skillsmart.hard.work.task65.case3.business;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Applicant {
    ApplicantId id;
    FIO fio;
}
