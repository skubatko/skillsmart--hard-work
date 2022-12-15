package ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.state;

import ru.skubatko.dev.skillsmart.hard.work.task15.case2.initial.TractorInDitchException;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.Position;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.Tractor;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.dictionary.Orientation;

import java.util.Objects;

public class EastState implements OrientationState {
    private final Tractor tractor;

    public EastState(Tractor tractor) {
        this.tractor = tractor;
    }

    @Override
    public void changeOnMoveForward() {
        Position position = tractor.getPosition();
        tractor.moveTo(position.getX() + 1, position.getY());
        if (tractor.getPosition().getX() >= tractor.getFieldSize().getWeight()) {
            throw new TractorInDitchException();
        }
    }

    @Override
    public void changeOnTurnClockwise() {
        tractor.setOrientationState(OrientationStateFactory.build(tractor, Orientation.SOUTH));
    }

    @Override
    public String getOrientation() {
        return "E";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof EastState)) {return false;}

        EastState eastState = (EastState) o;

        return Objects.equals(tractor, eastState.tractor);
    }

    @Override
    public int hashCode() {
        return tractor != null ? tractor.hashCode() : 0;
    }
}
