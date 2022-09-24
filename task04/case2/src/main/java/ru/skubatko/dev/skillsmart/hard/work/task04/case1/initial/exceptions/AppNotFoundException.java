package ru.skubatko.dev.skillsmart.hard.work.task04.case1.initial.exceptions;

import ru.skubatko.dev.skillsmart.hard.work.task04.case1.initial.enums.Errors;

public class AppNotFoundException extends RuntimeException {
    public AppNotFoundException(Errors p0, String p1) {
    }
}
