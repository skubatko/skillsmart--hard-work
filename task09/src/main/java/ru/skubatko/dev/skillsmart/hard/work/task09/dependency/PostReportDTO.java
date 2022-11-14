package ru.skubatko.dev.skillsmart.hard.work.task09.dependency;

import lombok.Data;

@Data
public class PostReportDTO {
    private String xEmployeeLogin;
    private String xEmployeePIN;
    private String type;
    private AppParametersDTO appParameters;
}
