package ru.skubatko.dev.skillsmart.hard.work.task54.case2.handlers;

public interface DiffHandler {

    void setNext(DiffHandler handler);

    void handle(DiffEntry diff, List<AffectedUnit> affected, List<UnhandledUnit> unhandled);
}
