package ru.skubatko.dev.skillsmart.hard.work.task70.case5.refactored;

public interface ApplicationDictionaryService {

    void processSearchParameters(ApplicationParametersDTO parametersDTO);

    String setFromDict(String value, String dictName);
}
