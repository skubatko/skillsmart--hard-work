package ru.skubatko.dev.skillsmart.hard.work.task61.case5.refactored;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class DictionaryMapperService {

    private String setFromDict(String value, String dictName) {
        if (isNullOrBlank(value)) {
            return value;
        }
        return Arrays.stream(value.split("\\s*,\\s*"))
            .map(param ->
                dictionaryValueToKeyCache.getOrDefault(dictName, Collections.emptyMap())
                    .getOrDefault(param, param))
            .collect(Collectors.joining(COMMA_DELIMITER));
    }
}
