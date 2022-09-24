package ru.skubatko.dev.skillsmart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TractorCommand {
    FORWARD("F"), TURN("T");

    private final String value;
}
