package ru.skubatko.dev.skillsmart.hard.work.task09.dependency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReportFileProcessDto {
    private Predicate predicate;
    private Integer reportId;
    private String reportFileId;
    private LocalDateTime reportCreatedDttm;
}
