package ru.skubatko.dev.skillsmart.hard.work.task60;

import java.util.Set;

public interface Flock<S extends Flyable<S>> {
    Set<S> join();
}
