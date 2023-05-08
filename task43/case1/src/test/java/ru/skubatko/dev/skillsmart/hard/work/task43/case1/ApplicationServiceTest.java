package ru.skubatko.dev.skillsmart.hard.work.task43.case1;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис управления заявками")
class ApplicationServiceTest {
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private ApplicationSearchPredicateBuilder predicateBuilder;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @DisplayName("должен обновлять состояние заявки по номеру")
    @Test
    void whenAddNewStateV2_thenApplicationStateHistoryUpdated() {
        // given
        ApplicationEntity application = getTestApplicationEntity();
        StateHistoryDTO state = createTestAppStateHistoryDto();
        when(applicationRepository.findByNumber(APP_NUMBER))
            .thenReturn(java.util.Optional.of(application));
        when(applicationRepository.save(any())).thenReturn(application);

        // when
        applicationService.addNewState(APP_NUMBER, state);

        // then
        verify(applicationRepository, times(1)).save(any());
    }
}
