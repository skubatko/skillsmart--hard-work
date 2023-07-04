package ru.skubatko.dev.skillsmart.hard.work.task54.case1.detectors;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class KafkaApiDetector extends AbstractApiDetector {
    private static final String KAFKA_LISTENER_ANNOTATION = "KafkaListener";
    private static final String TOPICS_ANNOTATION_VALUE_TYPE = "topics";
    private static final String GROUP_ID_ANNOTATION_VALUE_TYPE = "groupId";

    /**
     * {@inheritDoc}
     */
    @Override
    ApiType getApiType() {
        return ApiType.KAFKA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Optional<ApiDetails> getApiDetails(CtExecutable<?> executable) {
        log.trace("getApiDetails() - start: method = {}", SpoonHelper.getQualifiedName(executable));

        String topics = StringUtils.EMPTY;
        String groupId = StringUtils.EMPTY;

        Optional<CtAnnotation<? extends Annotation>> annotation =
                SpoonHelper.getAnnotationByPattern(executable, KAFKA_LISTENER_ANNOTATION);
        if (annotation.isPresent()) {
            topics = SpoonHelper.getAnnotationValue(TOPICS_ANNOTATION_VALUE_TYPE, annotation.get());
            groupId = SpoonHelper.getAnnotationValue(GROUP_ID_ANNOTATION_VALUE_TYPE, annotation.get());
        }

        if (topics.isEmpty() && groupId.isEmpty()) {
            log.trace("getApiDetails() - verdict: Kafka API not found");
            return Optional.empty();
        }

        log.trace("getApiDetails() - end: Kafka API found: topics = {}, groupId = {}", topics, groupId);
        return Optional.of(new KafkaApiDetails(topics, groupId));
    }
}
