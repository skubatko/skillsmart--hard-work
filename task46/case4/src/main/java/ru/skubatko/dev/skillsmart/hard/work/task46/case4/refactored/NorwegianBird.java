package ru.skubatko.dev.skillsmart.hard.work.task46.case4.refactored;

public class NorwegianBird extends Bird {
    private boolean isNailed;

    @Override
    public double getSpeed() {
        return isNailed ? 0 : getBaseSpeed();
    }
}
