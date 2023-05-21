package ru.skubatko.dev.skillsmart.hard.work.task46.case5.initial;

public class MedalService {

    public void display(String medalType) {
        if ("guest".equals(medalType)) {
            System.out.println("Distinguished guests Medal");
        } else if ("vip".equals(medalType)) {
            System.out.println("Medal of membership");
        } else if ("guard".equals(medalType)) {
            System.out.println("Display the guardian Medal");
        }
    }
}
