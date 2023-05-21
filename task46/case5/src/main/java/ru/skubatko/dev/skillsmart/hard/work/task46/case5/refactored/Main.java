package ru.skubatko.dev.skillsmart.hard.work.task46.case5.refactored;

public class Main {

    public static void main(String[] args) {
        String medalType = "guest";
        MedalService medalService = MedalServicesFactory.getMedalService(medalType);
        medalService.showMedal();
    }
}
