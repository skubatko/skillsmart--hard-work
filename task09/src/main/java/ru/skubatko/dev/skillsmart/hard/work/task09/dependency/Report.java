package ru.skubatko.dev.skillsmart.hard.work.task09.dependency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Report {

    private Integer reportId;

    private String userLogin;

    private String type;

    private Status status;

    private String errorText;

    private LocalDateTime createdDttm;

    private LocalDateTime lastUpdatedDttm;

    private AppParametersDTO requestParametersJson;

    private List<ReportFile> files;
}
