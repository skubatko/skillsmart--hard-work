package ru.skubatko.dev.skillsmart.hard.work.task08;

public class HalogenLight implements TimerLightable {
    private HalogenLightState state;

    @Override
    public void switchOn() {
        state = HalogenLightState.ON;
    }

    @Override
    public void switchOff() {
        state = HalogenLightState.OFF;
    }

    @Override
    public boolean isOn() {
        return state != HalogenLightState.OFF;
    }

    @Override
    public void turnOnFor(int duration) {
        System.out.println("Halogen light starting custom timer function ...");
        state = HalogenLightState.TIMER_MODE_ON;
        System.out.printf("Halogen light turning on for %d ... %n", duration);
        state = HalogenLightState.OFF;
        System.out.println("Halogen light finished custom timer function");
    }

    private enum HalogenLightState {
        OFF,
        ON,
        TIMER_MODE_ON
    }
}
