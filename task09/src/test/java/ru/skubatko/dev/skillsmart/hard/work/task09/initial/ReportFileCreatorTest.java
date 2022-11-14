package ru.skubatko.dev.skillsmart.hard.work.task09.initial;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.skubatko.dev.skillsmart.hard.work.task09.initial.StringUtils.toUpper;
import static ru.skubatko.dev.skillsmart.hard.work.task09.initial.StringUtils.upperFromString;

import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.AppParametersDTO;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ApplicationParametersMapper;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.Report;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ReportApplicationService;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ReportFile;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ReportFileProcessor;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ReportFileService;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ReportService;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReportFileCreatorTest {
    @Mock
    private ReportService reportService;
    @Mock
    private ReportFileService reportFileService;
    @Mock
    private ReportFileProcessor reportFileProcessor;
    @Mock
    private ReportApplicationService reportApplicationService;
    @Mock
    private ApplicationParametersMapper applicationParametersMapper;

    @InjectMocks
    private ReportFileCreator reportFileCreator;

    @SneakyThrows
    @Test
    void shouldCreateFile() {
        // given
        String xEmployeeLogin = "testEmployeeLogin";
        String xEmployeePIN = "testEmployeePIN";
        String type = "testType";
        AppParametersDTO appParameters = new AppParametersDTO();

        //when
        reportFileCreator.create(toUpper(xEmployeeLogin), upperFromString(xEmployeePIN), type, appParameters);

        // then
        verify(reportService, times(1)).create(any(Report.class));
        verify(reportFileService, times(1)).create(any(ReportFile.class));
    }
}
