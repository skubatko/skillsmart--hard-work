package ru.skubatko.dev.skillsmart.hard.work.task61.case3.initial;

import java.util.ArrayList;
import java.util.List;

public class ReferenceService {

    public List<String> findAllSaleChannels(String xEmployeePIN, boolean mapped) {
        if (mapped) {
            var infoTableValuesMap = dictionaryMapperService
                .getDictionaryKeyLowerCaseToValueCache().get(DICT_CM_CHANNEL_KIND);
            List<String> list = new ArrayList<>();
            for (String channel : applicationRepository.findAllSaleChannels()) {
                String info = infoTableValuesMap.getOrDefault(channel.toLowerCase(), channel);
                list.add(info);
            }
            return list;
        }
        return applicationRepository.findAllSaleChannels();
    }
}
