package ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.state;

public interface OrientationState {

    void changeOnMoveForward();

    void changeOnTurnClockwise();

    String getOrientation();
}
