package ru.skubatko.dev.skillsmart.hard.work.task43.case4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Сервис Reference")
@ExtendWith(MockitoExtension.class)
class ReferenceServiceTest {
    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ReferenceServiceImpl reference;

    @DisplayName("должен находить все состояния")
    @Test
    void findAllStates() {
        when(applicationRepository.findAllDistinctStates()).thenReturn(EMPTY_LIST);

        List<String> result = reference.findAllStates(X_EMPLOYEE_PIN);

        verify(applicationRepository, times(1)).findAllDistinctStates();
        assertEquals(EMPTY_LIST, result);
    }
}
