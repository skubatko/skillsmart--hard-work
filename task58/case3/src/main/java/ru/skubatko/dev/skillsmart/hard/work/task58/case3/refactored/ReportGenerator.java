package ru.skubatko.dev.skillsmart.hard.work.task58.case3.refactored;

import java.io.File;

public class ReportGenerator {

    public File generate(Report report) {
        return generatePdf(generateCsv(generateXml(report)));
    }

    private File generateCsv(File file) {
        System.out.println("генерация CSV файла");
        return null;
    }

    private File generatePdf(File file) {
        System.out.println("генерация PDF файла");
        return null;
    }

    private File generateXml(Report report) {
        System.out.println("генерация XML файла");
        return null;
    }

    public static void main(String[] args) {
        ReportGenerator reportGenerator = new ReportGenerator();
        Report report = new Report();
        reportGenerator.generate(report);
    }
}
