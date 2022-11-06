package ru.skubatko.dev.skillsmart.hard.work.task08;

public class Task08 {

    public static void main(String[] args) {
        OverheadLight overheadLight = new OverheadLight();
        HalogenLight halogenLight = new HalogenLight();
        LedLight ledLight = new LedLight();

        overheadLight.switchOn();
        overheadLight.switchOff();
        overheadLight.turnOnFor(3);
        overheadLight.blink(2, 2);

        halogenLight.turnOnFor(5);

        ledLight.blink(5, 3);
    }
}
