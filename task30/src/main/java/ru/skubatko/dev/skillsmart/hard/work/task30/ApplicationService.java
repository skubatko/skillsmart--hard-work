package ru.skubatko.dev.skillsmart.hard.work.task30;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final CarPurchasingRepository carPurchasingRepository;
    private final MessageService messageService;
    private final ApplicantMapper applicantMapper;
    private final ApplicationMapper applicationMapper;
    private final DictionaryMapperService dictionaryMapperService;
    private final ApplicationSearchPredicateBuilder predicateBuilder;

    public static final String BORROWER = "заемщик";
    public static final String CUSTOMER = "покупатель";
    public static final String APPLICATION_CHANNEL = "заявка";
    public static final String DEAL_CHANNEL = "сделка";

    public ApplicationDTO findByApplicationId(String xEmployeePIN, Long applicationId, boolean mapped) {
        return applicationRepository.findById(applicationId)
                .map(app -> mapped
                        ? ApplicationMapper.INSTANCE.toMappedDto(app, dictionaryMapperService)
                        : ApplicationMapper.INSTANCE.toDto(app))
                .orElseThrow(() -> new AppNotFoundException(
                        Errors.APPLICATION_NOT_FOUND,
                        messageService.getLocalizedMessage("application.not_found.alpp-comn-view-1") +
                                messageService.getLocalizedMessage("application.value.applicationId", applicationId.toString())));
    }

    public ApplicationDTO findByNumber(String number, boolean mapped) {
        return applicationRepository.findByNumber(number)
                .map(app -> mapped
                        ? ApplicationMapper.INSTANCE.toMappedDto(app, dictionaryMapperService)
                        : ApplicationMapper.INSTANCE.toDto(app))
                .orElseThrow(() -> new AppNotFoundException(
                        Errors.APPLICATION_NOT_FOUND,
                        messageService.getLocalizedMessage("application.not_found.alpp-comn-view-1") +
                                messageService.getLocalizedMessage("application.value.number", number))
                );
    }

    @AuditCollector(ALPP_COMN_APPLISTFRONT_REQUEST)
    public Iterable<ApplicationDTO> findByParams(
            String xEmployeePIN,
            String xEmployeeLogin,
            ApplicationParametersDTO parametersDTO,
            boolean mapped,
            Pageable pageable
    ) throws AppException {
        log.debug(MASKING_MARKER, "findByParams() - start: xEmployeePIN={}, parametersDTO={}, mapped={}, pageable={}",
                xEmployeePIN, parametersDTO, mapped, pageable);
        Sort sort = pageable.getSort();
        Sort sortByAppDate = Sort.by(Sort.Order.desc("appDate"));
        sort = sort.isUnsorted() ? sortByAppDate : sort.and(sortByAppDate);
        log.trace("findByParams() - trace: sort={}", sort);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        if (mapped) {
            dictionaryMapperService.processApplicationSearchParameters(parametersDTO);
        }
        Predicate whereExpression = predicateBuilder.findApplicationEntities(xEmployeePIN, xEmployeeLogin, parametersDTO);
        log.trace("findByParams() - trace: whereExpression={}", whereExpression);
        Page<ApplicationDTO> applicationDTOPage =
                applicationRepository.findAll(whereExpression, pageable)
                        .map(app -> mapped
                                ? ApplicationMapper.INSTANCE.toMappedDto(app, dictionaryMapperService)
                                : ApplicationMapper.INSTANCE.toDto(app));
        log.debug(MASKING_MARKER, "findByParams() - end: applicationDTOPage={}", applicationDTOPage);
        return applicationDTOPage;
    }

    @AuditCollector(ALPP_COMN_APPLISTPARTNERS_REQUEST)
    public Iterable<ApplicationInfoDTO> findAppInfoByParams(
            String xEmployeePIN,
            ApplicationInfoParametersDTO parametersDTO,
            boolean mapped, Pageable pageable
    ) {
        ApplicationParametersDTO applicationParametersDTO = new ApplicationParametersDTO();
        BeanUtils.copyProperties(parametersDTO, applicationParametersDTO);
        applicationParametersDTO.setFavouriteAppsFlag(false);
        if (mapped) {
            dictionaryMapperService.processApplicationSearchParameters(applicationParametersDTO);
        }
        Predicate whereExpression = predicateBuilder.findApplicationEntities(xEmployeePIN, null, applicationParametersDTO);
        return applicationRepository.findAll(whereExpression, pageable)
                .map(ApplicationMapper.INSTANCE::toDtoAppInfo);
    }

    public ApplicationOutputDTO save(
            String number,
            ApplicationInputDTO applicationInputDTO
    ) {
        CommonUtils.validate(applicationInputDTO, "statusCode", "appDate", "saleChannel");
        ApplicationEntity application = applicationPreparing(number, applicationInputDTO);
        return ApplicationMapper.INSTANCE.toDTOOutput(applicationRepository.save(application));
    }

    public void savePlain(String number, ApplicationInputDTO applicationInputDTO) {
        ApplicationEntity application = applicationPreparing(number, applicationInputDTO);
        applicationRepository.save(application);
    }

    public ApplicationOutputDTO addNewState(Long externalId, StateHistoryDTO state) {
        ApplicationEntity application = applicationRepository.findByExternalId(externalId)
                .orElseThrow(() -> new AppNotFoundException(
                        Errors.APPLICATION_NOT_FOUND,
                        messageService.getLocalizedMessage("application.not_found.alpp-comn-view-1") +
                                messageService.getLocalizedMessage("application.value.externalId", externalId.toString()))
                );

        return saveState(application, state);
    }

    public ApplicationOutputDTO addNewState(String number, StateHistoryDTO state) {
        ApplicationEntity application = applicationRepository.findByNumber(number)
                .orElseThrow(() -> new AppNotFoundException(
                        Errors.APPLICATION_NOT_FOUND,
                        messageService.getLocalizedMessage("application.not_found.alpp-comn-view-1") +
                                messageService.getLocalizedMessage("application.value.number", number))
                );

        return saveState(application, state);
    }

    private ApplicationEntity applicationPreparing(
            String number,
            ApplicationInputDTO applicationInputDTO
    ) {
        number = StringUtils.isBlank(number) ?
                applicationInputDTO.getNumber() : number;
        Long externalId = applicationInputDTO.getExternalId();
        String statusCode = applicationInputDTO.getStatusCode();
        String fillingBranchCode = applicationInputDTO.getFillingBranchCode();
        String issueBranch = applicationInputDTO.getIssueBranch();
        String purchaseRegion = applicationInputDTO.getPurchaseRegion();
        String term = applicationInputDTO.getTerm();
        String creditAmount = applicationInputDTO.getCreditAmount();
        String creditProgram = applicationInputDTO.getCreditProgram();
        String liabilityType = applicationInputDTO.getLiabilityType();
        LocalDateTime appDate = applicationInputDTO.getAppDate();
        LocalDateTime appLastUpDate = applicationInputDTO.getAppLastUpDate();
        String saleChannel = applicationInputDTO.getSaleChannel();
        String createdChannel = applicationInputDTO.getCreatedChannel();
        String dealer = applicationInputDTO.getDealer();
        String clientAddInfo = applicationInputDTO.getClientAddInfo();
        String paymentType = applicationInputDTO.getPaymentType();
        String clientPhone = applicationInputDTO.getClientPhone();
        String saleChannelSecondLevel = applicationInputDTO.getSaleChannelSecondLevel();
        String autoSalonCode = applicationInputDTO.getAutoSalonCode();
        BigDecimal maxMonthlyPayment = applicationInputDTO.getMaxMonthlyPayment();
        String productKindCode = applicationInputDTO.getProductKindCode();
        String productType = applicationInputDTO.getProductType();

        List<CreditProgramParamsDTO> creditProgramParams = Optional.ofNullable
                (applicationInputDTO.getCreditProgramParams()).orElse(new ArrayList<>());
        List<DiscountDTO> discounts = Optional.ofNullable
                (applicationInputDTO.getDiscounts()).orElse(new ArrayList<>());
        List<VehicleDTO> vehicles = Optional.ofNullable
                (applicationInputDTO.getVehicles()).orElse(new ArrayList<>());
        List<InsuranceDTO> insurances = Optional.ofNullable
                (applicationInputDTO.getInsurances()).orElse(new ArrayList<>());
        List<StateProgramDTO> statePrograms = Optional.ofNullable
                (applicationInputDTO.getStatePrograms()).orElse(new ArrayList<>());
        List<ApplicantDTO> applicants = Optional.ofNullable
                (applicationInputDTO.getApplicants()).orElse(new ArrayList<>());

        Long userCRMID = applicationInputDTO.getUserCRMID();
        String userFullName = applicationInputDTO.getUserFullName();
        String userLogin = applicationInputDTO.getUserLogin();
        String comment = applicationInputDTO.getComment();
        String createChannel = applicationInputDTO.getCreateChannel();
        String createChannelSecond = applicationInputDTO.getCreateChannelSecond();
        String sellerTag = applicationInputDTO.getSellerTag();
        String holdingTag = applicationInputDTO.getHoldingTag();

        Optional<ApplicationEntity> foundApplication = applicationRepository.findByNumber(number);
        ApplicationEntity application = foundApplication.orElse(new ApplicationEntity());

        String clientMdmId = "";
        String clientFullName = "";
        LocalDate clientBirthDate = null;
        for (ApplicantDTO applicantObj : applicants) {
            String clientSurname;
            String clientName;
            String clientMiddleName;
            if (applicantObj.getClientType().equalsIgnoreCase(BORROWER)
                    || applicantObj.getClientType().equalsIgnoreCase(CUSTOMER)
                    || createdChannel.equalsIgnoreCase(APPLICATION_CHANNEL)
                    || createdChannel.equalsIgnoreCase(DEAL_CHANNEL)) {
                // неименованный кусок кода
                clientMdmId = applicantObj.getMdmId();
                // неименованный кусок кода
                clientSurname = (StringUtils.isBlank(applicantObj.getSurname())) ?
                        "" : applicantObj.getSurname();
                clientName = (StringUtils.isBlank(applicantObj.getName())) ?
                        "" : " " + applicantObj.getName();
                clientMiddleName = (StringUtils.isBlank(applicantObj.getMiddleName())) ?
                        "" : " " + applicantObj.getMiddleName();
                // неименованный кусок кода
                clientFullName = clientSurname + clientName + clientMiddleName;
                // неименованный кусок кода
                clientBirthDate = applicantObj.getBirthDate();
                break;
            }
        }
        application.setRootValues(number, externalId, statusCode,
                fillingBranchCode, issueBranch, purchaseRegion,
                term, creditAmount, creditProgram, liabilityType,
                appDate, appLastUpDate, saleChannel, createdChannel,
                userFullName, clientMdmId,
                clientFullName, clientBirthDate,
                dealer, clientAddInfo, paymentType, clientPhone,
                saleChannelSecondLevel, maxMonthlyPayment, productKindCode, autoSalonCode,
                productType, userLogin,
                createChannel, createChannelSecond, sellerTag, holdingTag);

        List<ApplicantEntity> applicantsList = new ArrayList<>();
        for (ApplicantDTO applicantObj : applicants) {
            ApplicantEntity applicant = new ApplicantEntity();
            applicant.setMdmId(applicantObj.getMdmId());
            applicant.setClientType(applicantObj.getClientType());
            applicant.setSurname(applicantObj.getSurname());
            applicant.setName(applicantObj.getName());
            applicant.setMiddleName(applicantObj.getMiddleName());
            LocalDate locBirthDate = applicantObj.getBirthDate();
            applicant.setBirthDate(locBirthDate);
            applicant.setBirthPlace(applicantObj.getBirthPlace());
            if (nonNull(applicantObj.getSalaryClient())) {applicant.setSalaryClient(applicantObj.getSalaryClient());}
            applicant.setGender(applicantObj.getGender());
            applicant.setEmails(createEmails(applicantObj.getEmails(), applicant));
            applicant.setPhones(createPhones(applicantObj.getPhones(), applicant));
            applicant.setPreviousFIO(applicantObj.getPreviousFIO());
            if (nonNull(applicantObj.getChildrenDependentsQty())) {
                applicant.setChildrenDependentsQty(applicantObj.getChildrenDependentsQty());
            }
            applicant.setEducation(applicantObj.getEducation());
            applicant.setMarriageStatus(applicantObj.getMarriageStatus());
            applicant.setDealFlag(applicantObj.getDealFlag());
            applicant.setApplication(application);
            // неименованный кусок кода
            if (nonNull(applicantObj.getAddresses())) {
                applicant.setAddresses(collectionMapper(applicantObj.getAddresses(), addr -> {
                    ApplicantAddressEntity address = applicantMapper.toAddressEntity(addr);
                    address.setApplicant(applicant);
                    return address;
                }));
            }
            if (nonNull(applicantObj.getEmployments())) {
                applicant.setEmployments(collectionMapper(applicantObj.getEmployments(), empl -> {
                    ApplicantEmploymentEntity employment = applicantMapper.toEmploymentEntity(empl);
                    employment.setApplicant(applicant);
                    return employment;
                }));
            }
            // неименованный кусок кода
            if (nonNull(applicantObj.getIncomes())) {
                applicant.setIncomes(collectionMapper(applicantObj.getIncomes(), income -> {
                    ApplicantIncomeEntity incomeEntity = applicantMapper.toIncomeEntity(income);
                    incomeEntity.setApplicant(applicant);
                    return incomeEntity;
                }));
            }
            if (nonNull(applicantObj.getPassports())) {
                applicant.setPassports(collectionMapper(applicantObj.getPassports(), pass -> {
                    ApplicantPassportEntity passport = applicantMapper.toPassportEntity(pass);
                    passport.setApplicant(applicant);
                    return passport;
                }));
            }
            applicantsList.add(applicant);
        }
        application.getApplicants().clear();
        application.getApplicants().addAll(applicantsList);

        List<CreditProgramParamsEntity> creditProgramParamsList = new ArrayList<>();
        for (CreditProgramParamsDTO creditProgramParamObj : creditProgramParams) {
            CreditProgramParamsEntity creditProgramParam = new CreditProgramParamsEntity();
            creditProgramParam.setFundingRate(creditProgramParamObj.getFundingRate());
            creditProgramParam.setFixedRate(creditProgramParamObj.getFixedRate());
            creditProgramParam.setRequestedAmount(creditProgramParamObj.getRequestedAmount());
            creditProgramParam.setInitialFee(creditProgramParamObj.getInitialFee());
            creditProgramParam.setCreditDate(creditProgramParamObj.getCreditDate());
            creditProgramParam.setProgramDescription(creditProgramParamObj.getProgramDescription());
            creditProgramParam.setApplication(application);
            creditProgramParamsList.add(creditProgramParam);
        }
        application.getCreditProgramParams().clear();
        application.getCreditProgramParams().addAll(creditProgramParamsList);

        List<DiscountEntity> discountsList = new ArrayList<>();
        for (DiscountDTO discountObj : discounts) {
            DiscountEntity discount = new DiscountEntity();
            discount.setValue(discountObj.getValue());
            discount.setType(discountObj.getType());
            discount.setDiscountOrAllowance(discountObj.getDiscountOrAllowance());
            discount.setApplication(application);
            discountsList.add(discount);
        }
        application.getDiscounts().clear();
        application.getDiscounts().addAll(discountsList);

        List<InsuranceEntity> insurancesList = new ArrayList<>();
        for (InsuranceDTO insuranceObj : insurances) {
            InsuranceEntity insurance = new InsuranceEntity();
            insurance.setTypeCode(insuranceObj.getTypeCode());
            insurance.setIncludeToLoan(insuranceObj.getIncludeToLoan());
            insurance.setPeriod(insuranceObj.getPeriod());
            insurance.setPremium(insuranceObj.getPremium());
            insurance.setAmount(insuranceObj.getAmount());
            insurance.setProgram(insuranceObj.getProgram());
            insurance.setCompany(insuranceObj.getCompany());
            insurance.setApplication(application);
            insurancesList.add(insurance);
        }
        application.getInsurances().clear();
        application.getInsurances().addAll(insurancesList);

        List<StateProgramEntity> stateProgramsList = new ArrayList<>();
        for (StateProgramDTO stateProgramObj : statePrograms) {
            StateProgramEntity stateProgram = new StateProgramEntity();
            stateProgram.setTypeCode(stateProgramObj.getTypeCode());
            stateProgram.setDiscount(stateProgramObj.getDiscount());
            stateProgram.setApplication(application);
            stateProgramsList.add(stateProgram);
        }
        application.getStatePrograms().clear();
        application.getStatePrograms().addAll(stateProgramsList);

        List<VehicleEntity> vehiclesList = new ArrayList<>();
        for (VehicleDTO vehicleObj : vehicles) {
            VehicleEntity vehicle = new VehicleEntity();
            vehicle.setType(vehicleObj.getType());
            vehicle.setCategory(vehicleObj.getCategory());
            vehicle.setMark(vehicleObj.getMark());
            vehicle.setModel(vehicleObj.getModel());
            vehicle.setAmount(vehicleObj.getAmount());
            vehicle.setVin(vehicleObj.getVin());
            vehicle.setApplication(application);
            vehiclesList.add(vehicle);
        }
        application.getVehicles().clear();
        application.getVehicles().addAll(vehiclesList);

        StateHistoryEntity stateHistory = new StateHistoryEntity(
                statusCode, appLastUpDate, userCRMID,
                userFullName, comment, createdChannel, application
        );
        application.getStateHistory().add(stateHistory);

        application.getDecisions().clear();
        if (nonNull(applicationInputDTO.getDecisions())) {
            application.getDecisions().addAll(collectionMapper(applicationInputDTO.getDecisions(), dec -> {
                DecisionEntity decisionEntity = applicationMapper.toEntity(dec);
                decisionEntity.setApplication(application);
                return decisionEntity;
            }));
        }

        setCarPurchasingEntity(applicationInputDTO, application);

        return application;
    }

    private List<ApplicantPhoneEntity> createPhones(List<ApplicantPhoneDTO> phones, ApplicantEntity applicant) {
        if (isNullOrEmpty(phones)) {
            return null;
        }
        return phones.stream()
                .map(phone -> {
                    ApplicantPhoneEntity applicantPhoneEntity = new ApplicantPhoneEntity();
                    applicantPhoneEntity.setType(phone.getType());
                    applicantPhoneEntity.setValue(phone.getValue());
                    applicantPhoneEntity.setApplicant(applicant);
                    return applicantPhoneEntity;
                })
                .collect(Collectors.toList());
    }

    private List<ApplicantEmailEntity> createEmails(List<ApplicantEmailDTO> emails, ApplicantEntity applicant) {
        if (isNullOrEmpty(emails)) {
            return null;
        }
        return emails.stream()
                .map(email -> {
                    ApplicantEmailEntity applicantEmailEntity = new ApplicantEmailEntity();
                    applicantEmailEntity.setType(email.getType());
                    applicantEmailEntity.setValue(email.getValue());
                    applicantEmailEntity.setApplicant(applicant);
                    return applicantEmailEntity;
                })
                .collect(Collectors.toList());
    }

    private void setCarPurchasingEntity(ApplicationInputDTO applicationInputDTO, ApplicationEntity application) {
        Optional.ofNullable(applicationInputDTO.getBuyCar())
                .ifPresent(purchase -> {
                    CarPurchasingEntity carPurchasing = application.getCarPurchasingEntity();
                    if (carPurchasing == null) {
                        carPurchasing = new CarPurchasingEntity();
                        carPurchasing.setApplicationEntity(application);
                    }
                    carPurchasing.setAdvtId(applicationInputDTO.getBuyCar().getAdvtId());
                    application.setCarPurchasingEntity(carPurchasingRepository.save(carPurchasing));
                });
    }


    private ApplicationOutputDTO saveState(
            ApplicationEntity application,
            StateHistoryDTO state
    ) {
        String statusCode = state.getStatusCode();
        LocalDateTime appLastUpDate = state.getStateDate();
        Long userCRMID = state.getUserCRMID();
        String userFullName = state.getUserFullName();
        String comment = state.getComment();
        String createdChannel = state.getCreatedChannel();

        application.setStatusCode(statusCode);
        application.setAppLastUpDate(appLastUpDate);

        StateHistoryEntity stateHistory = new StateHistoryEntity(
                statusCode, appLastUpDate, userCRMID,
                userFullName, comment, createdChannel, application
        );
        application.getStateHistory().add(stateHistory);

        return ApplicationMapper.INSTANCE.toDTOOutput(applicationRepository.save(application));
    }
}
