package ru.skubatko.dev.skillsmart.hard.work.task43.case5;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Сервис очистки таблиц отчетов и файлов отчетов")
class ReportsFilesHousekeepingServiceITCase extends AbstractITCase {
    @Autowired
    private ReportsFilesHousekeepingService service;
    @Autowired
    private ReportService reportService;
    @Autowired
    private ReportFileService reportFileService;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ReportFileRepository reportFileRepository;

    @DisplayName("должен очищать таблицы")
    @SneakyThrows
    @Test
    void shouldClearReportTablesExpectedly() {
        // given
        val report = reportService.create(testReport());
        reportFileService.create(testReportFile().setReport(report));
        reportFileService.create(testReportFile2().setReport(report));

        assertThat(reportRepository.count()).isEqualTo(1L);
        assertThat(reportFileRepository.count()).isEqualTo(2L);

        // when
        service.perform();

        // then
        assertThat(reportRepository.count()).isEqualTo(0);
        assertThat(reportFileRepository.count()).isEqualTo(0);
    }
}
