package ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.state;

import ru.skubatko.dev.skillsmart.hard.work.task15.case2.initial.TractorInDitchException;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.Position;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.Tractor;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.dictionary.Orientation;

import java.util.Objects;

public class NorthState implements OrientationState {
    private final Tractor tractor;

    public NorthState(Tractor tractor) {
        this.tractor = tractor;
    }

    @Override
    public void changeOnMoveForward() {
        Position position = tractor.getPosition();
        tractor.moveTo(position.getX(), position.getY() + 1);
        if (tractor.getPosition().getY() >= tractor.getFieldSize().getHeight()) {
            throw new TractorInDitchException();
        }
    }

    @Override
    public void changeOnTurnClockwise() {
        tractor.setOrientationState(OrientationStateFactory.build(tractor, Orientation.EAST));
        tractor.setOrientationState(OrientationStateFactory.build(tractor, Orientation.EAST));
    }

    @Override
    public String getOrientation() {
        return "N";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof NorthState)) {return false;}

        NorthState that = (NorthState) o;

        return Objects.equals(tractor, that.tractor);
    }

    @Override
    public int hashCode() {
        return tractor != null ? tractor.hashCode() : 0;
    }
}
