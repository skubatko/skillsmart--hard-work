package ru.skubatko.dev.skillsmart.hard.work.task04.case3.initial;

import lombok.Data;

import java.util.Map;

@Data
public class DictionaryMapperService {
    private Map<Dictionary, Map<String, String>> dictionaryKeyLowerCaseToValueCache;
}
