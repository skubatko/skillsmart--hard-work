package ru.skubatko.dev.skillsmart.hard.work.task65.case1.business;

import lombok.Builder;
import lombok.Value;

import java.nio.channels.Channel;

@Value
@Builder(toBuilder = true)
public class Application {
    ApplicationId id;
    ApplicationNumber number;
    Channel channel;
    ApplicantSet applicants;
}
