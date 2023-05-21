package ru.skubatko.dev.skillsmart.hard.work.task46.case4.refactored;

public class AfricanBird extends Bird {

    @Override
    public double getSpeed() {
        return getBaseSpeed() - getLoadFactor();
    }
}
