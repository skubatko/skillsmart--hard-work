package ru.skubatko.dev.skillsmart.hard.work.task03.case2.src.main.java.ru.skubatko.dev.skillsmart;

public interface TractorMovable {

    void move();

    boolean isApplicable(TractorCommand command);
}
