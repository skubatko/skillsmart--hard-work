package ru.skubatko.dev.skillsmart.hard.work.task70.case5.initial;

import java.util.Map;

public interface DictionaryMapperService {

    void processApplicationSearchParameters(ApplicationParametersDTO parametersDTO);

    String setDictValueById(String dictionaryName, @Nullable String dictionaryId);

    Map<String, String> getMapByDictionaryLowerCaseKey(String dictId);

    Map<String, String> getMapByDictionaryValueToKey(String dictId);

    String setFromDict(String value, String dictName);

    void cacheUpdate();
}
