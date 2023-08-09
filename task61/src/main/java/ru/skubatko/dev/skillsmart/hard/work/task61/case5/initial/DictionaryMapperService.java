package ru.skubatko.dev.skillsmart.hard.work.task61.case5.initial;

import java.util.Collections;
import java.util.StringJoiner;

public class DictionaryMapperService {

    private String setFromDict(String value, String dictName) {
        if (isNullOrBlank(value)) {
            return value;
        }
        StringJoiner joiner = new StringJoiner(COMMA_DELIMITER);
        for (String param : value.split("\\s*,\\s*")) {
            String keyCache = dictionaryValueToKeyCache.getOrDefault(dictName, Collections.emptyMap())
                .getOrDefault(param, param);
            joiner.add(keyCache);
        }
        return joiner.toString();
    }
}
