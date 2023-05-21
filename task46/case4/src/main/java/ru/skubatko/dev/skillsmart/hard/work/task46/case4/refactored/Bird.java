package ru.skubatko.dev.skillsmart.hard.work.task46.case4.refactored;

public abstract class Bird {

    public abstract double getSpeed();

    protected double getLoadFactor() {
        return 3;
    }

    protected double getBaseSpeed() {
        return 10;
    }
}
