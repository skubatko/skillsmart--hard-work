package ru.skubatko.dev.skillsmart.hard.work.task04.case3.initial;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ReferenceService {
    private final ApplicationRepository applicationRepository;
    private final DictionaryMapperService dictionaryMapperService;

    public List<String> findAllStates(String xEmployeePIN) {
        return applicationRepository.findAllDistinctStates();
    }

    public List<String> findAllRegions(String xEmployeePIN, boolean mapped) {
        if (mapped) {
            var infoTableValuesMap = dictionaryMapperService
                .getDictionaryKeyLowerCaseToValueCache().get(Dictionary.DICT_CM_REGION);
            return applicationRepository.findAllDistinctRegions().stream()
                .map(reg -> infoTableValuesMap.getOrDefault(reg.toLowerCase(), reg))
                .collect(Collectors.toList());
        }
        return applicationRepository.findAllDistinctRegions();
    }

    public List<String> findAllCreatedChannels(String xEmployeePIN) {
        return applicationRepository.findAllDistinctCreatedChannels();
    }

    public List<String> findAllSaleChannels(String xEmployeePIN, boolean mapped) {
        if (mapped) {
            var infoTableValuesMap = dictionaryMapperService
                .getDictionaryKeyLowerCaseToValueCache().get(Dictionary.DICT_CM_CHANNEL_KIND);
            return applicationRepository.findAllSaleChannels().stream()
                .map(channel -> infoTableValuesMap.getOrDefault(channel.toLowerCase(), channel))
                .collect(Collectors.toList());
        }
        return applicationRepository.findAllSaleChannels();
    }

    public List<String> findAllSaleChannelsSecondLevel(String xEmployeePIN) {
        return applicationRepository.findAllDistinctSaleChannelsSecondLevel();
    }

    public List<String> findAllIssueBranches(String xEmployeePIN, boolean mapped) {
        if (mapped) {
            var infoTableValuesMap = dictionaryMapperService
                .getDictionaryKeyLowerCaseToValueCache().get(Dictionary.DICT_CM_ORGANIZATION_UNIT);
            return applicationRepository.findAllDistinctIssueBranches().stream()
                .map(branch -> infoTableValuesMap.getOrDefault(branch.toLowerCase(), branch))
                .collect(Collectors.toList());
        }
        return applicationRepository.findAllDistinctIssueBranches();
    }

    public List<String> findAllLiabilityTypes(String xEmployeePIN, boolean mapped) {
        if (mapped) {
            var infoTableValuesMap = dictionaryMapperService
                .getDictionaryKeyLowerCaseToValueCache().get(Dictionary.DICT_CM_LIABILITY_TYPE);
            return applicationRepository.findAllDistinctLiabilityTypes().stream()
                .map(type -> infoTableValuesMap.getOrDefault(type.toLowerCase(), type))
                .collect(Collectors.toList());
        }
        return applicationRepository.findAllDistinctLiabilityTypes();
    }
}
