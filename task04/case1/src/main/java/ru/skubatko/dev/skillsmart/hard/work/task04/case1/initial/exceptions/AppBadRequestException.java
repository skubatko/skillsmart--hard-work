package ru.skubatko.dev.skillsmart.hard.work.task04.case1.initial.exceptions;

import ru.skubatko.dev.skillsmart.hard.work.task04.case1.initial.enums.Errors;

public class AppBadRequestException extends RuntimeException {
    public AppBadRequestException(Errors p0, String localizedMessage) {}
}
