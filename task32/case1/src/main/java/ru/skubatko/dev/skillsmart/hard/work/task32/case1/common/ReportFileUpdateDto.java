package ru.skubatko.dev.skillsmart.hard.work.task32.case1.common;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class ReportFileUpdateDto {
    UUID ecmFileId;
    LocalDateTime ecmExpirationDate;
    FileFormat format;
    BigDecimal size;
    Status status;
    LocalDateTime ecmFileTransferDttm;
    String errorText;
}
