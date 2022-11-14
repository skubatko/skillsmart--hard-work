package ru.skubatko.dev.skillsmart.hard.work.task09.initial;

import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.AppParametersDTO;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ApplicationParametersMapper;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.Report;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ReportApplicationService;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ReportFile;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ReportFileProcessDto;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ReportFileProcessor;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ReportFileService;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ReportFileUpdateDto;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ReportService;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ReportUpdateDto;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.Status;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@RequiredArgsConstructor
public class ReportFileCreator {
    private final ReportService reportService;
    private final ReportFileService reportFileService;
    private final ReportFileProcessor reportFileProcessor;
    private final ReportApplicationService reportApplicationService;
    private final ApplicationParametersMapper applicationParametersMapper;

    private static final String ERROR_TEXT = "";

    public boolean create(String xEmployeeLogin, String xEmployeePIN, String type, AppParametersDTO appParameters) {
        log.debug("create() - start: xEmployeeLogin: {}, xEmployeePIN: {}, type: {}, appParameters: {}",
            xEmployeeLogin, xEmployeePIN, type, appParameters);
        val report = reportService.create(
            new Report()
                .setUserLogin(xEmployeeLogin)
                .setType(type)
                .setStatus(Status.IN_QUEUE)
                .setRequestParametersJson(appParameters));
        val reportFile = reportFileService.create(
            new ReportFile()
                .setReport(report)
                .setStatus(Status.IN_QUEUE));
        log.trace("create() - trace: report file status changed to: {}", Status.IN_QUEUE);

        if (reportApplicationService.count(
            reportApplicationService.getPredicate(
                xEmployeePIN,
                applicationParametersMapper.toApplicationParametersDTO(appParameters))) == 0) {
            reportService.update(
                report.getReportId(),
                ReportUpdateDto.builder()
                    .status(Status.ERROR)
                    .errorText(ERROR_TEXT)
                    .build());
            reportFileService.update(
                reportFile.getGuid(),
                ReportFileUpdateDto.builder()
                    .status(Status.ERROR)
                    .errorText(ERROR_TEXT)
                    .build());
            log.trace("create() - verdict: applications are not found");
            log.trace("create() - trace: report file status changed to: {}", Status.ERROR);
            return false;
        }

        reportFileProcessor.process(
            ReportFileProcessDto.builder()
                .predicate(reportApplicationService.getPredicate(
                    xEmployeePIN,
                    applicationParametersMapper.toApplicationParametersDTO(appParameters)))
                .reportId(report.getReportId())
                .reportFileId(reportFile.getGuid())
                .reportCreatedDttm(report.getCreatedDttm())
                .build());
        log.debug("create() - end");
        return true;
    }
}
