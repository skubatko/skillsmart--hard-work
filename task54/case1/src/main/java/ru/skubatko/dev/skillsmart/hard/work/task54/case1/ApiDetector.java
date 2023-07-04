package ru.skubatko.dev.skillsmart.hard.work.task54.case1;

import java.util.Optional;

public interface ApiDetector {

    void setNext(ApiDetector detector);

    Optional<ApiUnit> detect(AffectedUnit affectedUnit);
}
