package ru.skubatko.dev.skillsmart.hard.work.task61.case3.refactored;

import java.util.List;
import java.util.stream.Collectors;

public class ReferenceService {

    public List<String> findAllSaleChannels(String xEmployeePIN, boolean mapped) {
        if (mapped) {
            var infoTableValuesMap = dictionaryMapperService
                .getDictionaryKeyLowerCaseToValueCache().get(DICT_CM_CHANNEL_KIND);
            return applicationRepository.findAllSaleChannels().stream()
                .map(channel -> infoTableValuesMap.getOrDefault(channel.toLowerCase(), channel))
                .collect(Collectors.toList());
        }
        return applicationRepository.findAllSaleChannels();
    }
}
