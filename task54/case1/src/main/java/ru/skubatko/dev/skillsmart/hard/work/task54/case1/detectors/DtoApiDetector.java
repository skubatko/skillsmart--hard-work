package ru.skubatko.dev.skillsmart.hard.work.task54.case1.detectors;

import ru.skubatko.dev.skillsmart.hard.work.task54.case1.ApiDetector;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class DtoApiDetector implements ApiDetector {
    private static final List<String> DTO_PACKAGE_SIGN = new ArrayList<>();

    static {
        DTO_PACKAGE_SIGN.add("dto");
        DTO_PACKAGE_SIGN.add("pojo");
        DTO_PACKAGE_SIGN.add("model");
    }

    private ApiDetector next;

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
        log.trace("detect() - start: affected method = {}", SpoonHelper.getQualifiedName(affectedUnit.getAffectedMethod()));
        CtExecutable<?> executable = affectedUnit.getAffectedMethod();
        String qualifiedName = executable.getReference().getDeclaringType().getQualifiedName();
        if (DTO_PACKAGE_SIGN.stream().anyMatch(qualifiedName::contains)) {
            log.trace("detect() - verdict: found DTO API, ignored");
            return Optional.empty();
        }

        log.trace("detect() - verdict: DTO API not found");
        return next.detect(affectedUnit);
    }
}
