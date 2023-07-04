package ru.skubatko.dev.skillsmart.hard.work.task54.case1;

import ru.skubatko.dev.skillsmart.hard.work.task54.case1.detectors.DtoApiDetector;
import ru.skubatko.dev.skillsmart.hard.work.task54.case1.detectors.KafkaApiDetector;
import ru.skubatko.dev.skillsmart.hard.work.task54.case1.detectors.UndefinedApiDetector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiDetectorChain {

    private final ApiDetector head;

    public ApiDetectorChain() {
        ApiDetector dtoApiEnhancer = new DtoApiDetector();
        ApiDetector kafkaApiEnhancer = new KafkaApiDetector();
        ApiDetector tail = new UndefinedApiDetector();

        head.setNext(dtoApiEnhancer);
        dtoApiEnhancer.setNext(kafkaApiEnhancer);
        kafkaApiEnhancer.setNext(tail);
    }

    public Optional<ApiUnit> detect(AffectedUnit affectedUnit) {
        log.debug("detect() - start: affected method = {}",
            SpoonHelper.getQualifiedName(affectedUnit.getAffectedMethod()));

        Optional<ApiUnit> optionalApiUnit = head.detect(affectedUnit);

        if (optionalApiUnit.isPresent()) {
            ApiUnit apiUnit = optionalApiUnit.get();
            log.debug("detect() - end: found API = {}, details = {}", apiUnit.getApiType(), apiUnit.getApiDetails());
        } else {
            log.debug("detect() - end: API not found");
        }

        return optionalApiUnit;
    }
}
