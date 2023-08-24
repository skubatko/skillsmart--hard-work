package ru.skubatko.dev.skillsmart.hard.work.task65.case1.report;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ApplicationReportTO {
    String number;
    String channel;
}
