package ru.skubatko.dev.skillsmart.hard.work.task46.case3.refactored;

public class AddCommand implements Command {
    private final int a;
    private final int b;

    public AddCommand(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer execute() {
        return a + b;
    }
}
