package ru.skubatko.dev.skillsmart.hard.work.task58.case1.refactored;

import java.util.function.Function;

public class IncomeCalculator {

    public double income(Employee employee) {
        return extraBonus(bonus(salary(employee)));
    }

    public double incomeV2(Employee employee) {
        Function<Employee, Double> f1 = this::salary;
        Function<Double, Double> f2 = this::bonus;
        Function<Double, Double> f3 = this::extraBonus;
        return f1.andThen(f2).andThen(f3).apply(employee);
    }

    private double salary(Employee e) {
        System.out.println("расчёт зп");
        return 10.0;
    }

    private double bonus(double salary) {
        System.out.println("расчёт премии");
        return salary + 2.0;
    }

    private double extraBonus(double bonus) {
        System.out.println("расчёт доп премии");
        return bonus + 0.7;
    }

    public static void main(String[] args) {
        var employee = new Employee();
        var calculator = new IncomeCalculator();
        System.out.println("====V1====");
        System.out.println(calculator.income(employee));
        System.out.println("====V2====");
        System.out.println(calculator.incomeV2(employee));
    }
}
