package ru.skubatko.dev.skillsmart.hard.work.task70.case4.initial;

public interface ReportService {

    ReportsMetadataListDTO findReportsByParams(String userLogin, String type);

    ReportEntity create(ReportEntity report);

    void update(Integer id, ReportUpdateDto dto);
}
