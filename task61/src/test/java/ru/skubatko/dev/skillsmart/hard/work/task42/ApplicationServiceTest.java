package ru.skubatko.dev.skillsmart.hard.work.task42;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс ApplicationService")
class ApplicationServiceTest {
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private ApplicationSearchPredicateBuilder predicateBuilder;

    @InjectMocks
    private ApplicationService applicationService;

    private static final long APPLICATION_ID = 1L;
    private static final String NUMBER = "testPin";
    private static final Long EXTERNAL_ID = 3L;
    private static final String APP_NUMBER = "12";
    private static final Pageable PAGEABLE = PageRequest.of(0, 10);
    public static final String CLIENT_MDM_ID = "clientMdmId";
    public static final String CLIENT_SURNAME = "clientSurname";
    public static final String CLIENT_MIDDLE_NAME = "clientMiddleName";
    public static final String CLIENT_NAME = "clientName";

    @DisplayName("Сохранит заявку")
    @Test
    void whenSave_thenApplicationSaved() {
        ApplicationInputDTO appInputDto = createTestApplicationInputDto();
        when(applicationRepository.findByNumber(APP_NUMBER))
            .thenReturn(java.util.Optional.empty());
        applicationService.save(APP_NUMBER, appInputDto);

        verify(applicationRepository, times(1)).save(any());
    }

    @DisplayName("Обновит состояние заявки по externalId")
    @Test
    void whenAddNewStateV1_thenApplicationStateHistoryUpdated() {
        ApplicationEntity application = getTestApplicationEntity();
        StateHistoryDTO state = createTestAppStateHistoryDto();
        when(applicationRepository.findByExternalId(EXTERNAL_ID))
            .thenReturn(java.util.Optional.of(application));
        when(applicationRepository.save(any())).thenReturn(application);

        applicationService.addNewState(EXTERNAL_ID, state);

        verify(applicationRepository, times(1)).save(any());
    }

    @DisplayName("Обновит состояние заявки по number")
    @Test
    void whenAddNewStateV2_thenApplicationStateHistoryUpdated() {
        ApplicationEntity application = getTestApplicationEntity();
        StateHistoryDTO state = createTestAppStateHistoryDto();
        when(applicationRepository.findByNumber(APP_NUMBER))
            .thenReturn(java.util.Optional.of(application));
        when(applicationRepository.save(any())).thenReturn(application);

        applicationService.addNewState(APP_NUMBER, state);

        verify(applicationRepository, times(1)).save(any());
    }

    @DisplayName("Найдет заявки")
    @Test
    void whenFindByParams_thenApplicationFound() {
        ApplicationEntity application = getTestApplicationEntity();
        List<ApplicationEntity> applications = new ArrayList<>();
        applications.add(application);
        Page<ApplicationEntity> applicationPage = new PageImpl<>(applications, PAGEABLE, applications.size());
        when(applicationRepository.findAll(any(), (Pageable) any())).thenReturn(applicationPage);

        Iterable<ApplicationDTO> result =
            applicationService.findByParams(NUMBER, "X_EMPLOYEE_LOGIN", null, false, PAGEABLE);

        assertEquals(1, Iterables.size(result));
        verify(applicationRepository, times(1)).findAll(any(), (Pageable) any());
    }

    @DisplayName("Найдет заявки")
    @Test
    void whenFindAppByParams_thenApplicationFound() {
        ApplicationEntity application = getTestApplicationEntity();
        ApplicationInfoParametersDTO parametersDTO = new ApplicationInfoParametersDTO()
            .surname(CLIENT_SURNAME)
            .name(CLIENT_NAME)
            .middleName(CLIENT_MIDDLE_NAME);
        ApplicantEntity applicant = new ApplicantEntity();
        applicant.setClientType("type");
        applicant.setSurname(CLIENT_SURNAME);
        applicant.setName(CLIENT_NAME);
        applicant.setMiddleName(CLIENT_MIDDLE_NAME);
        application.getApplicants().add(applicant);
        List<ApplicationEntity> applications = new ArrayList<>();
        applications.add(application);
        Page<ApplicationEntity> applicationPage = new PageImpl<>(applications, PAGEABLE, applications.size());
        when(applicationRepository.findAll(any(), (Pageable) any())).thenReturn(applicationPage);
        when(predicateBuilder.findApplicationEntities(anyString(), any(), any(ApplicationParametersDTO.class)))
            .thenReturn(new BooleanBuilder(Expressions.asBoolean(true).isTrue()));

        Iterable<ApplicationInfoDTO> result =
            applicationService.findAppInfoByParams(NUMBER, parametersDTO, false, PAGEABLE);

        verify(applicationRepository, times(1)).findAll(any(), (Pageable) any());
        assertEquals(1, Iterables.size(result));
        assertThat(result.iterator().next())
            .hasFieldOrPropertyWithValue("applicationId", APPLICATION_ID)
            .hasFieldOrPropertyWithValue("mdmId", CLIENT_MDM_ID)
            .hasFieldOrPropertyWithValue("surname", CLIENT_SURNAME)
            .hasFieldOrPropertyWithValue("name", CLIENT_NAME)
            .hasFieldOrPropertyWithValue("middleName", CLIENT_MIDDLE_NAME);
    }

    @DisplayName("Найдет заявку по applicationId")
    @Test
    void whenFindByApplicationId_thenApplicationFound() {
        ApplicationEntity application = getTestApplicationEntity();
        when(applicationRepository.findById(any())).thenReturn(java.util.Optional.of(application));

        applicationService.findByApplicationId(NUMBER, APPLICATION_ID, false);

        verify(applicationRepository, times(1)).findById(any());
    }
}
