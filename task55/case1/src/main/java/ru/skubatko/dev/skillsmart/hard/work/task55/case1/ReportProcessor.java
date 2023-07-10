package ru.skubatko.dev.skillsmart.hard.work.task55.case1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class ReportProcessor {
    private final ReportService reportService;
    private final ReportFileService reportFileService;
    private final ReportPrinter reportPrinter;
    private final EcmStorage ecmStorage;

    private static final String FILE_EXTENSION = ".csv";
    private static final char SEMICOLON = ';';
    private static final double BYTES_PER_KILOBYTE_D = 1024.0;
    private static final double BYTES_PER_MEGABYTE_D = 1048576.0;

    @Async
    public void process(ReportFileProcessDto dto) {
        log.debug("process() - start: dto: {}", dto);
        Path file = null;
        try {
            file = Files.createTempFile(null, FILE_EXTENSION);
            log.trace("process() - trace: created file: {}", file);
            log.debug("create() - start");

            // создание отчёта
            () -> {
                try (
                    BufferedWriter bufferedWriter = Files.newBufferedWriter(file);
                    CSVPrinter csvPrinter = getCsvFormat().print(bufferedWriter)
                ) {
                    csvPrinter.printRecord(ReportFileColumnHeaders.headerRow);
                    reportPrinter.print(csvPrinter, dto.getPredicate());
                    reportService.update(
                        dto.getReportId(),
                        ReportUpdateDto.builder().status(Status.CREATED).build());
                    log.trace("create() - trace: report status changed to: {}", Status.CREATED);
                    reportFileService.update(
                        dto.getReportFileId(),
                        ReportFileUpdateDto.builder().status(Status.CREATED).build());
                    log.trace("create() - trace: report file status changed to: {}", Status.CREATED);
                    log.debug("create() - end");
                }
            }
            log.trace("process() - trace: report created");

            // сохранение файла с отчётом
            () -> {
                log.debug("store() - start: file: {}", file);
                reportService.update(
                    dto.getReportId(),
                    ReportUpdateDto.builder().status(Status.UPLOADING).build());
                log.trace("store() - trace: report status changed to: {}", Status.UPLOADING);
                reportFileService.update(
                    dto.getReportFileId(),
                    ReportFileUpdateDto.builder()
                        .ecmExpirationDate(ecmStorage.getExpirationDate(dto.getReportCreatedDttm()))
                        .format(FileFormat.CSV)
                        .size(getSize(file))
                        .status(Status.UPLOADING)
                        .ecmFileTransferDttm(LocalDateTime.now())
                        .build());
                log.trace("store() - trace: report file status changed to: {}", Status.UPLOADING);
                ecmStorage.store(file, dto);
                log.debug("store() - end");
            }
            log.trace("process() - trace: report stored");
        } catch (IOException ex) {
            log.debug("process() - verdict: report process interrupted");
            throw new AppException(IO_EXCEPTION, CommonUtils.findRootCause(ex));
        } finally {
            // удаление файла с отчётом
            () -> {
                log.debug("delete() - start: file: {}", file);
                if (isNull(file)) {
                    log.debug("delete() - verdict: file does not exist");
                    return;
                }

                try {
                    Files.delete(file);
                    log.debug("delete() - verdict: completed");
                } catch (IOException ex) {
                    log.warn("delete() - verdict: failed for file: {}", file, ex);
                }
                log.debug("delete() - end");
            }
            log.trace("process() - trace: file delete completed");
        }
        log.debug("process() - end");
    }

    @NotNull
    private CSVFormat getCsvFormat() {
        return CSVFormat.DEFAULT.withDelimiter(SEMICOLON).withQuoteMode(QuoteMode.ALL);
    }

    private BigDecimal getSize(Path file) {
        long sizeBytes = file.toFile().length();
        BigDecimal sizeKb = BigDecimal.valueOf(sizeBytes / BYTES_PER_KILOBYTE_D).setScale(2, RoundingMode.CEILING);
        BigDecimal sizeMb = BigDecimal.valueOf(sizeBytes / BYTES_PER_MEGABYTE_D).setScale(2, RoundingMode.CEILING);
        log.trace("getSize() - trace: file size bytes: {}, Kb: {}, Mb: {}", sizeBytes, sizeKb, sizeMb);
        return sizeMb;
    }
}
