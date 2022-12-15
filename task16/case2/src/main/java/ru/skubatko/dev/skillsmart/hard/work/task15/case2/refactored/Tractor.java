package ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.dictionary.Orientation;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.state.OrientationState;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.state.OrientationStateFactory;

public class Tractor {
    private final FieldSize fieldSize;
    private final Position position;
    private OrientationState orientationState;

    public Tractor() {
        this.fieldSize = new FieldSize(5, 5);
        this.position = new Position(0, 0);
        this.orientationState = OrientationStateFactory.build(this, Orientation.NORTH);
    }

    public Tractor(FieldSize fieldSize, Position position, OrientationState orientationState) {
        this.fieldSize = fieldSize;
        this.position = position;
        this.orientationState = orientationState;
    }

    public void moveForward() {
        System.out.println(">> move forward");
        orientationState.changeOnMoveForward();
    }

    public void turnClockwise() {
        System.out.println(">> turn clockwise");
        orientationState.changeOnTurnClockwise();
    }

    public Position getPosition() {
        return position;
    }

    public FieldSize getFieldSize() {
        return fieldSize;
    }

    public OrientationState getOrientationState() {
        return orientationState;
    }

    public void setOrientationState(OrientationState orientationState) {
        this.orientationState = orientationState;
    }

    public String getOrientation() {
        return orientationState.getOrientation();
    }

    public void moveTo(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }
}
