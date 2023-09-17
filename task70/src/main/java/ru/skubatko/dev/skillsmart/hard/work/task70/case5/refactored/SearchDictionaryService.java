package ru.skubatko.dev.skillsmart.hard.work.task70.case5.refactored;

import java.util.Map;

public interface SearchDictionaryService {

    Map<String, String> getMapByLowerCaseKey(String dictId);

    Map<String, String> getMapByValue(String dictId);
}
