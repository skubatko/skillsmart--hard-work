package ru.skubatko.dev.skillsmart.hard.work.task46.case3.refactored;

public class DivideCommand implements Command {
    private final int a;
    private final int b;

    public DivideCommand(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer execute() {
        return a / b;
    }
}
