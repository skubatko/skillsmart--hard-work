package ru.skubatko.dev.skillsmart.hard.work.task08;

public class LedLight implements BlinkingLightable, TimerLightable {
    private boolean isOn;

    public LedLight() {
        this.isOn = false;
    }

    @Override
    public void switchOn() {
        System.out.println("LED Light switching on ...");
        isOn = true;
    }

    @Override
    public void switchOff() {
        System.out.println("LED Light switching off ...");
        isOn = false;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void blink(int duration, int repeatCount) {
        System.out.println("LED Light starting the custom blink function ...");
        for (int i = 0; i < repeatCount; i++) {
            turnOnFor(duration);
        }
        System.out.println("LED Light has finished the custom blink function!");
    }
}
