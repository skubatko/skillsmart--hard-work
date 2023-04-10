package ru.skubatko.dev.skillsmart.hard.work.task35.case4;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
public class ReportPrinter {
    private final ReportApplicationService reportApplicationService;
    private final ReportFileMapper reportFileMapper;

    public void print(CSVPrinter csvPrinter, Predicate predicate) {
        long size = reportApplicationService.count(predicate);
        val counter = new AtomicInteger();
        reportApplicationService.findAll(predicate).forEach(app -> {
            log.trace("print() - trace: printing application: {}, {} of {}",
                    app.getNumber(), counter.incrementAndGet(), size);
            printApp(csvPrinter, app);
        });
    }

    private void printApp(CSVPrinter csvPrinter, ApplicationDTO app) {
        try {
            csvPrinter.printRecord(reportFileMapper.map(app));
        } catch (IOException ex) {
            log.debug("printApp() - verdict: report process interrupted");
            throw new AppException(IO_EXCEPTION, CommonUtils.findRootCause(ex));
        }
    }
}
