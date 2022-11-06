package ru.skubatko.dev.skillsmart.hard.work.task08;

public interface BlinkingLightable extends Lightable {

    default void blink(int duration, int repeatCount) {
        System.out.println("Default interface method for blinking");
        for (int count = 0; count < repeatCount; count++) {
            switchOn();
            System.out.printf("ON ... wait for %d ...%n", duration);
            switchOff();
            System.out.printf("OFF .. wait for %d ...%n", duration);
        }
        System.out.println("Done with the default interface method for blinking");
    }
}
