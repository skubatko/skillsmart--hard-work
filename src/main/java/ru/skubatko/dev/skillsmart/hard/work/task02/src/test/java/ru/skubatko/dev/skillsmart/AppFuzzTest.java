package ru.skubatko.dev.skillsmart;

import dev.fuzzit.javafuzz.core.AbstractFuzzTarget;

public class AppFuzzTest extends AbstractFuzzTarget {

    @Override
    public void fuzz(byte[] data) {
        App.parseComplex(data);
    }
}
