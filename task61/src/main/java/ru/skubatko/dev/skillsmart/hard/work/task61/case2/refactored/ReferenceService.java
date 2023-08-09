package ru.skubatko.dev.skillsmart.hard.work.task61.case2.refactored;

import java.util.List;
import java.util.stream.Collectors;

public class ReferenceService {

    public List<String> findAllRegions(String xEmployeePIN, boolean mapped) {
        if (mapped) {
            var infoTableValuesMap = dictionaryMapperService
                .getDictionaryKeyLowerCaseToValueCache().get(DICT_CM_REGION);
            return applicationRepository.findAllDistinctRegions().stream()
                .map(reg -> infoTableValuesMap.getOrDefault(reg.toLowerCase(), reg))
                .collect(Collectors.toList());
        }
        return applicationRepository.findAllDistinctRegions();
    }
}
