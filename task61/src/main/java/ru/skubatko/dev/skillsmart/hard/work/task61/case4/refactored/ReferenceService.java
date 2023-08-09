package ru.skubatko.dev.skillsmart.hard.work.task61.case4.refactored;

import java.util.List;
import java.util.stream.Collectors;

public class ReferenceService {

    public List<String> findAllIssueBranches(String xEmployeePIN, boolean mapped) {
        if (mapped) {
            var infoTableValuesMap = dictionaryMapperService
                .getDictionaryKeyLowerCaseToValueCache().get(DICT_CM_ORGANIZATION_UNIT);
            return applicationRepository.findAllDistinctIssueBranches().stream()
                .map(branch -> infoTableValuesMap.getOrDefault(branch.toLowerCase(), branch))
                .collect(Collectors.toList());
        }
        return applicationRepository.findAllDistinctIssueBranches();
    }
}
