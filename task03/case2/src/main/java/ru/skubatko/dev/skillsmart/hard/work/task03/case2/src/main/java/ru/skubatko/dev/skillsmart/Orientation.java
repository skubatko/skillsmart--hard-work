package ru.skubatko.dev.skillsmart.hard.work.task03.case2.src.main.java.ru.skubatko.dev.skillsmart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Orientation {
    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

    private final String value;
}
