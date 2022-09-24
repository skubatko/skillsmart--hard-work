package ru.skubatko.dev.skillsmart;

public interface TractorMovable {

    void move();

    boolean isApplicable(TractorCommand command);
}
