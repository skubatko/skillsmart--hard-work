package ru.skubatko.dev.skillsmart.hard.work.task70.case4.refactored;

public interface SearchReportService {

    ReportsMetadataListDTO findReportsByParams(String userLogin, String type);
}
