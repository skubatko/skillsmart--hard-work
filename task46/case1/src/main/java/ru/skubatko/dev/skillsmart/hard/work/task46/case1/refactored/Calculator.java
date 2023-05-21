package ru.skubatko.dev.skillsmart.hard.work.task46.case1.refactored;

public class Calculator {

    public int calculate(int a, int b, String operator) {
        Operation targetOperation = OperatorFactory
            .getOperation(operator)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
        return targetOperation.apply(a, b);
    }
}
