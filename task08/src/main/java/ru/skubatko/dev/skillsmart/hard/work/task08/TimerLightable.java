package ru.skubatko.dev.skillsmart.hard.work.task08;

public interface TimerLightable extends Lightable {

    default void turnOnFor(int duration) {
        System.out.println("Default interface method for turning on for ...");
        switchOn();
        System.out.printf("wait for %d ...%n", duration);
        switchOff();
        System.out.println("Done with the default interface method for for turning on for");
    }
}
