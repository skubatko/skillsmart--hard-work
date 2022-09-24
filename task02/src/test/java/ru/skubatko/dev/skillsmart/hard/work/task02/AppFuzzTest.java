package ru.skubatko.dev.skillsmart.hard.work.task02;

import dev.fuzzit.javafuzz.core.AbstractFuzzTarget;

public class AppFuzzTest extends AbstractFuzzTarget {

    @Override
    public void fuzz(byte[] data) {
        App.parseComplex(data);
    }
}
