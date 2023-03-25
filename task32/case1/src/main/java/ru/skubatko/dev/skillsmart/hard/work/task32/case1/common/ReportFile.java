package ru.skubatko.dev.skillsmart.hard.work.task32.case1.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReportFile {
    private UUID guid;
    private UUID ecmFileId;
    private LocalDateTime ecmExpirationDttm;
    private FileFormat format;
    private BigDecimal size;
    private Status status;
    private String errorText;
    private LocalDateTime createdDttm;
    private LocalDateTime lastUpdatedDttm;
    private LocalDateTime ecmFileTransferDttm;
    private Report report;
}
