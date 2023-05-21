package ru.skubatko.dev.skillsmart.hard.work.task46.case3.refactored;

public class Calculator {

    public int calculate(Command command) {
        return command.execute();
    }
}
