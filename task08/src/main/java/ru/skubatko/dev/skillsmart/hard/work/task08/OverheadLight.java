package ru.skubatko.dev.skillsmart.hard.work.task08;

public class OverheadLight implements TimerLightable, BlinkingLightable {
    private boolean isOn;

    public OverheadLight() {
        this.isOn = false;
    }

    @Override
    public void switchOn() {
        System.out.println("OverheadLight switching on ...");
        isOn = true;
    }

    @Override
    public void switchOff() {
        System.out.println("OverheadLight switching off ...");
        isOn = false;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public String toString() {
        return "OverheadLight{" +
            "isOn=" + isOn +
            '}';
    }
}
