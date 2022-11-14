package ru.skubatko.dev.skillsmart.hard.work.task09.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.ApplicationParametersMapper;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.PostReportDTO;
import ru.skubatko.dev.skillsmart.hard.work.task09.dependency.Predicate;
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
public class ReportFileCreatorRefactored {
    private final ReportService reportService;
    private final ReportFileService reportFileService;
    private final ReportFileProcessor reportFileProcessor;
    private final ReportApplicationService reportApplicationService;
    private final ApplicationParametersMapper applicationParametersMapper;

    private static final String ERROR_TEXT = "";

    public void create(PostReportDTO postReportDTO) {
        log.debug("create() - start: postReportDTO: {}", postReportDTO);
        val report = reportService.create(
            new Report()
                .setUserLogin(postReportDTO.getXEmployeeLogin())
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
        Predicate predicate = reportApplicationService.getPredicate(postReportDTO.getXEmployeePIN(), applicationParametersDTO);
        if (reportApplicationService.count(predicate) == 0) {
            reportService.update(report.getReportId(),
                ReportUpdateDto.builder()
                    .status(Status.ERROR)
                    .errorText(ERROR_TEXT)
                    .build());
            reportFileService.update(reportFile.getGuid(),
                ReportFileUpdateDto.builder()
                    .status(Status.ERROR)
                    .errorText(ERROR_TEXT)
                    .build());
            log.trace("create() - verdict: applications are not found");
            log.trace("create() - trace: report file status changed to: {}", Status.ERROR);
            return;
        }

        reportFileProcessor.process(
            ReportFileProcessDto.builder()
                .predicate(predicate)
                .reportId(report.getReportId())
                .reportFileId(reportFile.getGuid())
                .reportCreatedDttm(report.getCreatedDttm())
                .build());
        log.debug("create() - end");
    }
}
