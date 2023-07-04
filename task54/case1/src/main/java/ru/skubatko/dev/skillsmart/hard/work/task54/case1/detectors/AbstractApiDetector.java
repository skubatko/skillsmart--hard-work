package ru.skubatko.dev.skillsmart.hard.work.task54.case1.detectors;

import ru.skubatko.dev.skillsmart.hard.work.task54.case1.ApiDetector;

import java.util.Optional;

abstract class AbstractApiDetector implements ApiDetector {

    ApiDetector next;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNext(ApiDetector detector) {
        this.next = detector;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ApiUnit> detect(AffectedUnit affectedUnit) {
        CtExecutable<?> executable = affectedUnit.getAffectedMethod();
        Optional<ApiDetails> details = getApiDetails(executable);
        if (details.isPresent()) {
            ApiUnit apiUnit = new ApiUnit();
            apiUnit.setApiType(getApiType());
            apiUnit.setApiDetails(details.get());
            apiUnit.setAffectedUnit(affectedUnit);

            return Optional.of(apiUnit);
        }

        return next.detect(affectedUnit);
    }

    abstract ApiType getApiType();

    abstract Optional<ApiDetails> getApiDetails(CtExecutable<?> executable);
}
