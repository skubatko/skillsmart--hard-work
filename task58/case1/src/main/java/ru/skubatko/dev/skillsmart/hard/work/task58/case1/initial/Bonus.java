package ru.skubatko.dev.skillsmart.hard.work.task58.case1.initial;

public class Bonus extends Salary {

    @Override
    public double calc() {
        System.out.println("рассчитываем премию");
        return super.calc() + 0.12;
    }
}
