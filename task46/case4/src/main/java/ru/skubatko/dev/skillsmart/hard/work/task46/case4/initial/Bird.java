package ru.skubatko.dev.skillsmart.hard.work.task46.case4.initial;

public class Bird {

    private enum Species {
        EUROPEAN, AFRICAN, NORWEGIAN_BLUE;
    }

    private boolean isNailed;
    private Species type;

    public double getSpeed() {
        if (type == Species.EUROPEAN) {
            return getBaseSpeed();
        } else if (type == Species.AFRICAN) {
            return getBaseSpeed() - getLoadFactor();
        } else if (type == Species.NORWEGIAN_BLUE) {
            return isNailed ? 0 : getBaseSpeed();
        }
        return 0;
    }

    private double getLoadFactor() {
        return 3;
    }

    private double getBaseSpeed() {
        return 10;
    }
}
