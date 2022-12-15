package ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.state;

import ru.skubatko.dev.skillsmart.hard.work.task15.case2.initial.TractorInDitchException;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.Position;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.Tractor;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.dictionary.Orientation;

import java.util.Objects;

public class WestState implements OrientationState {
    private final Tractor tractor;

    public WestState(Tractor tractor) {
        this.tractor = tractor;
    }

    @Override
    public void changeOnMoveForward() {
        Position position = tractor.getPosition();
        tractor.moveTo(position.getX() - 1, position.getY());
        if (tractor.getPosition().getX() < 0) {
            throw new TractorInDitchException();
        }
    }

    @Override
    public void changeOnTurnClockwise() {
        tractor.setOrientationState(OrientationStateFactory.build(tractor, Orientation.NORTH));
    }

    @Override
    public String getOrientation() {
        return "W";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof WestState)) {return false;}

        WestState westState = (WestState) o;

        return Objects.equals(tractor, westState.tractor);
    }

    @Override
    public int hashCode() {
        return tractor != null ? tractor.hashCode() : 0;
    }
}
