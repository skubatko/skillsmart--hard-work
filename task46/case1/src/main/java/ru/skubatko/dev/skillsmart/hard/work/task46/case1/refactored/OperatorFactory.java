package ru.skubatko.dev.skillsmart.hard.work.task46.case1.refactored;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OperatorFactory {
    static Map<String, Operation> operationMap = new HashMap<>();

    static {
        operationMap.put("add", new Addition());
        operationMap.put("multiply", new Multiplication());
        operationMap.put("divide", new Division());
        operationMap.put("subtract", new Subtraction());
    }

    public static Optional<Operation> getOperation(String operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }
}
