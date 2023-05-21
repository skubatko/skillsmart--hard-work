package ru.skubatko.dev.skillsmart.hard.work.task46.case3.refactored;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int result = calculator.calculate(new AddCommand(3, 7));
        System.out.println(result);
    }
}
