package ru.skubatko.dev.skillsmart.hard.work.task42;

import java.awt.print.Pageable;

public class ApplicationService {

    public ApplicationDTO findByApplicationId(String number, Long applicationId, boolean mapped) {
        return applicationRepository.findById(applicationId)
            .map(app -> mapped
                ? ApplicationMapper.INSTANCE.toMappedDto(app, dictionaryMapperService)
                : ApplicationMapper.INSTANCE.toDto(app))
            .orElseThrow();
    }

    public ApplicationDTO findByNumber(String number, boolean mapped) {
        return applicationRepository.findByNumber(number)
            .map(app -> mapped
                ? ApplicationMapper.INSTANCE.toMappedDto(app, dictionaryMapperService)
                : ApplicationMapper.INSTANCE.toDto(app))
            .orElseThrow();
    }

    public Iterable<ApplicationDTO> findByParams(
        String number,
        String login,
        ApplicationParametersDTO parametersDTO,
        boolean mapped,
        Pageable pageable
    ) throws AppException {
        Sort sort = pageable.getSort();
        Sort sortByAppDate = Sort.by(Sort.Order.desc("appDate"));
        sort = sort.isUnsorted() ? sortByAppDate : sort.and(sortByAppDate);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        if (mapped) {
            dictionaryMapperService.processApplicationSearchParameters(parametersDTO);
        }
        Predicate whereExpression = predicateBuilder.findApplicationEntities(number, login, parametersDTO);
        Page<ApplicationDTO> applicationDTOPage =
            applicationRepository.findAll(whereExpression, pageable)
                .map(app -> mapped
                    ? ApplicationMapper.INSTANCE.toMappedDto(app, dictionaryMapperService)
                    : ApplicationMapper.INSTANCE.toDto(app));
        return applicationDTOPage;
    }

    public Iterable<ApplicationInfoDTO> findAppInfoByParams(
        String number,
        ApplicationInfoParametersDTO parametersDTO,
        boolean mapped, Pageable pageable
    ) {
        ApplicationParametersDTO applicationParametersDTO = new ApplicationParametersDTO();
        BeanUtils.copyProperties(parametersDTO, applicationParametersDTO);
        applicationParametersDTO.setFavouriteAppsFlag(false);
        if (mapped) {
            dictionaryMapperService.processApplicationSearchParameters(applicationParametersDTO);
        }
        Predicate whereExpression = predicateBuilder.findApplicationEntities(number, null, applicationParametersDTO);
        return applicationRepository.findAll(whereExpression, pageable)
            .map(ApplicationMapper.INSTANCE::toDtoAppInfo);
    }

    public ApplicationOutputDTO save(
        String number,
        ApplicationInputDTO applicationInputDTO
    ) {
        CommonUtils.validate(applicationInputDTO, "statusCode", "appDate", "saleChannel");
        ApplicationEntity application = applicationPreparing(number, applicationInputDTO);
        ApplicationEntity saveApplication = applicationRepository.save(application);
        setGovProgramEntity(applicationInputDTO, saveApplication);
        return ApplicationMapper.INSTANCE.toDTOOutput(saveApplication);
    }

    public void savePlain(String number, ApplicationInputDTO applicationInputDTO) {
        ApplicationEntity application = applicationPreparing(number, applicationInputDTO);
        ApplicationEntity saveApplication = applicationRepository.save(application);
        setGovProgramEntity(applicationInputDTO, saveApplication);
    }

    public ApplicationOutputDTO addNewState(Long externalId, StateHistoryDTO state) {
        ApplicationEntity application = applicationRepository.findByExternalId(externalId).orElseThrow();
        return saveState(application, state);
    }

    public ApplicationOutputDTO addNewState(String number, StateHistoryDTO state) {
        ApplicationEntity application = applicationRepository.findByNumber(number).orElseThrow();
        return saveState(application, state);
    }
}
