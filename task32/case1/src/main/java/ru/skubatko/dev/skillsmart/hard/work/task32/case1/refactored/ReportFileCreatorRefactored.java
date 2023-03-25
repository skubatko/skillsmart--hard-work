package ru.skubatko.dev.skillsmart.hard.work.task32.case1.refactored;

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

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Predicate;

@Slf4j
@RequiredArgsConstructor
public class ReportFileCreatorRefactored {
    private final ReportService reportService;
    private final ReportFileService reportFileService;
    private final ReportProcessor reportProcessor;
    private final ReportApplicationService reportApplicationService;
    private final ApplicationParametersMapper applicationParametersMapper;

    public void create(String xEmployeePIN, String xEmployeeLogin, PostReportDTO postReportDTO) {
        log.debug("create() - start: xEmployeeLogin: {}, xEmployeePIN: {}, postReportDTO: {}",
            xEmployeeLogin, xEmployeePIN, postReportDTO);
        val report = getReport(xEmployeeLogin, postReportDTO);
        val reportFile = getReportFile(report);
        Predicate predicate = getPredicate(xEmployeePIN, xEmployeeLogin, postReportDTO);
        validate(predicate, report, reportFile);
        Integer reportId = report.getReportId();
        UUID reportFileId = reportFile.getGuid();
        LocalDateTime createdDttm = report.getCreatedDttm();
        reportProcessor.process(
            ReportFileProcessDto.builder()
                .predicate(predicate)
                .reportId(reportId)
                .reportFileId(reportFileId)
                .reportCreatedDttm(createdDttm)
                .build());
        log.debug("create() - end");
    }

    private Report getReport(String xEmployeeLogin, PostReportDTO postReportDTO) {
        return reportService.create(
            new Report()
                .setUserLogin(xEmployeeLogin)
                .setType(postReportDTO.getType())
                .setStatus(Status.IN_QUEUE)
                .setRequestParametersJson(postReportDTO.getAppParameters()));
    }

    private ReportFile getReportFile(Report report) {
        return reportFileService.create(
            new ReportFile()
                .setReport(report)
                .setStatus(Status.IN_QUEUE));
    }

    private Predicate getPredicate(String xEmployeePIN, String xEmployeeLogin, PostReportDTO postReportDTO) {
        val applicationParametersDTO =
            applicationParametersMapper.toApplicationParametersDTO(postReportDTO.getAppParameters());
        return reportApplicationService.getPredicate(xEmployeePIN, xEmployeeLogin, applicationParametersDTO);
    }

    private void validate(Predicate predicate, Report report, ReportFile reportFile) {
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
            throw new IllegalStateException();
        }
    }
}
