package ru.skubatko.dev.skillsmart.hard.work.task61.case2.initial;

import java.util.ArrayList;
import java.util.List;

public class ReferenceService {

    public List<String> findAllRegions(String xEmployeePIN, boolean mapped) {
        if (mapped) {
            var infoTableValuesMap = dictionaryMapperService
                .getDictionaryKeyLowerCaseToValueCache().get(DICT_CM_REGION);
            List<String> list = new ArrayList<>();
            for (String reg : applicationRepository.findAllDistinctRegions()) {
                String info = infoTableValuesMap.getOrDefault(reg.toLowerCase(), reg);
                list.add(info);
            }
            return list;
        }
        return applicationRepository.findAllDistinctRegions();
    }
}
