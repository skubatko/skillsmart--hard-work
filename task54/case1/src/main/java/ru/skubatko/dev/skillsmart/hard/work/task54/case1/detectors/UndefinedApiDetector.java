package ru.skubatko.dev.skillsmart.hard.work.task54.case1.detectors;

import ru.skubatko.dev.skillsmart.hard.work.task54.case1.ApiDetector;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class UndefinedApiDetector extends AbstractApiDetector {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNext(ApiDetector detector) {
        throw new FinalApiDetectorChainLinkException("this is the final chain, please set before chain = " + detector);
    }

    @Override
    ApiType getApiType() {
        return ApiType.UNDEFINED;
    }

    @Override
    Optional<ApiDetails> getApiDetails(CtExecutable<?> executable) {
        log.trace("getApiDetails() - start: method = {}", SpoonHelper.getQualifiedName(executable));

        List<String> classAnnotations = getAnnotationNames(executable.getParent().getAnnotations());
        List<String> methodAnnotations = getAnnotationNames(executable.getAnnotations());

        log.trace("getApiDetails() - end: Undefined API found: classAnnotations = {}, methodAnnotations = {}",
                classAnnotations, methodAnnotations);

        return Optional.of(new UndefinedApiDetails(classAnnotations, methodAnnotations));
    }

    private List<String> getAnnotationNames(List<CtAnnotation<? extends Annotation>> annotations) {
        List<String> annotationNames = new ArrayList<>();
        for (CtAnnotation<? extends Annotation> annotation : annotations) {
            annotationNames.add(annotation.getType().getQualifiedName());
        }
        return annotationNames;
    }
}
