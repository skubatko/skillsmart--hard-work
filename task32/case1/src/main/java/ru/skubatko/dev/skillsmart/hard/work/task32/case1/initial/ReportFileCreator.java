package ru.skubatko.dev.skillsmart.hard.work.task32.case1.initial;

import ru.skubatko.dev.skillsmart.hard.work.task32.case1.common.ApplicationParametersMapper;
import ru.skubatko.dev.skillsmart.hard.work.task32.case1.common.PostReportDTO;
import ru.skubatko.dev.skillsmart.hard.work.task32.case1.common.Report;
import ru.skubatko.dev.skillsmart.hard.work.task32.case1.common.ReportApplicationService;
import ru.skubatko.dev.skillsmart.hard.work.task32.case1.common.ReportFile;
import ru.skubatko.dev.skillsmart.hard.work.task32.case1.common.ReportFileProcessDto;
import ru.skubatko.dev.skillsmart.hard.work.task32.case1.common.ReportFileService;
import ru.skubatko.dev.skillsmart.hard.work.task32.case1.common.ReportFileUpdateDto;
import ru.skubatko.dev.skillsmart.hard.work.task32.case1.common.ReportProcessor;
import ru.skubatko.dev.skillsmart.hard.work.task32.case1.common.ReportService;
import ru.skubatko.dev.skillsmart.hard.work.task32.case1.common.ReportUpdateDto;
import ru.skubatko.dev.skillsmart.hard.work.task32.case1.common.Status;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.function.Predicate;

@Slf4j
@RequiredArgsConstructor
public class ReportFileCreator {
    private final ReportService reportService;
    private final ReportFileService reportFileService;
    private final ReportProcessor reportProcessor;
    private final ReportApplicationService reportApplicationService;
    private final ApplicationParametersMapper applicationParametersMapper;

    public void create(String xEmployeePIN, String xEmployeeLogin, PostReportDTO postReportDTO) {
        log.debug("create() - start: xEmployeeLogin: {}, xEmployeePIN: {}, postReportDTO: {}",
            xEmployeeLogin, xEmployeePIN, postReportDTO);
        val report = reportService.create(
            new Report()
                .setUserLogin(xEmployeeLogin)
                .setType(postReportDTO.getType())
                .setStatus(Status.IN_QUEUE)
                .setRequestParametersJson(postReportDTO.getAppParameters()));
        val reportFile = reportFileService.create(
            new ReportFile()
                .setReport(report)
                .setStatus(Status.IN_QUEUE));
        log.trace("create() - trace: report file status changed to: {}", Status.IN_QUEUE);

        val applicationParametersDTO =
            applicationParametersMapper.toApplicationParametersDTO(postReportDTO.getAppParameters());
        Predicate predicate = reportApplicationService.getPredicate(xEmployeePIN, xEmployeeLogin, applicationParametersDTO);
        if (reportApplicationService.count(predicate) == 0) {
            reportService.update(report.getReportId(),
                ReportUpdateDto.builder()
                    .status(Status.ERROR)
                    .errorText("ERROR_TEXT")
                    .build());
            reportFileService.update(reportFile.getGuid(),
                ReportFileUpdateDto.builder()
                    .status(Status.ERROR)
                    .errorText("ERROR_TEXT")
                    .build());
            log.trace("create() - verdict: applications are not found");
            log.trace("create() - trace: report file status changed to: {}", Status.ERROR);
            return;
        }

        reportProcessor.process(
            ReportFileProcessDto.builder()
                .predicate(predicate)
                .reportId(report.getReportId())
                .reportFileId(reportFile.getGuid())
                .reportCreatedDttm(report.getCreatedDttm())
                .build());
        log.debug("create() - end");
    }
}
