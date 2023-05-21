package ru.skubatko.dev.skillsmart.hard.work.task46.case2.refactored;

public class Calculator {

    public int calculate(int a, int b, Operator operator) {
        return operator.apply(a, b);
    }
}
