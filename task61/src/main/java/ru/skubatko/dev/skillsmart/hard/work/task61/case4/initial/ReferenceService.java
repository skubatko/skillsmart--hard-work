package ru.skubatko.dev.skillsmart.hard.work.task61.case4.initial;

import java.util.ArrayList;
import java.util.List;

public class ReferenceService {

    public List<String> findAllIssueBranches(String xEmployeePIN, boolean mapped) {
        if (mapped) {
            var infoTableValuesMap = dictionaryMapperService
                .getDictionaryKeyLowerCaseToValueCache().get(DICT_CM_ORGANIZATION_UNIT);
            List<String> list = new ArrayList<>();
            for (String branch : applicationRepository.findAllDistinctIssueBranches()) {
                String info = infoTableValuesMap.getOrDefault(branch.toLowerCase(), branch);
                list.add(info);
            }
            return list;
        }
        return applicationRepository.findAllDistinctIssueBranches();
    }
}
