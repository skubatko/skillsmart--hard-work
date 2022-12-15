package ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored;

import static org.assertj.core.api.Assertions.assertThat;

import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.state.EastState;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.state.NorthState;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.state.SouthState;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.state.WestState;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

class TractorTest {

    @SneakyThrows
    @Test
    void shouldMoveExpectedly() {
        Tractor tractor = new Tractor();
        NorthState northState = new NorthState(tractor);
        EastState eastState = new EastState(tractor);
        SouthState southState = new SouthState(tractor);
        WestState westState = new WestState(tractor);

        assertThat(tractor.getPosition()).isEqualTo(new Position(0, 0));
        assertThat(tractor.getOrientation()).isEqualTo("N");
        assertThat(tractor.getOrientationState()).isEqualTo(northState);

        tractor.turnClockwise();
        assertThat(tractor.getPosition()).isEqualTo(new Position(0, 0));
        assertThat(tractor.getOrientation()).isEqualTo("E");
        assertThat(tractor.getOrientationState()).isEqualTo(eastState);

        tractor.moveForward();
        assertThat(tractor.getPosition()).isEqualTo(new Position(1, 0));
        assertThat(tractor.getOrientation()).isEqualTo("E");
        assertThat(tractor.getOrientationState()).isEqualTo(eastState);

        tractor.turnClockwise();
        assertThat(tractor.getPosition()).isEqualTo(new Position(1, 0));
        assertThat(tractor.getOrientation()).isEqualTo("S");
        assertThat(tractor.getOrientationState()).isEqualTo(southState);

        tractor.turnClockwise();
        assertThat(tractor.getPosition()).isEqualTo(new Position(1, 0));
        assertThat(tractor.getOrientation()).isEqualTo("W");
        assertThat(tractor.getOrientationState()).isEqualTo(westState);

        tractor.turnClockwise();
        assertThat(tractor.getPosition()).isEqualTo(new Position(1, 0));
        assertThat(tractor.getOrientation()).isEqualTo("N");
        assertThat(tractor.getOrientationState()).isEqualTo(northState);

        tractor.moveForward();
        assertThat(tractor.getPosition()).isEqualTo(new Position(1, 1));
        assertThat(tractor.getOrientation()).isEqualTo("N");
        assertThat(tractor.getOrientationState()).isEqualTo(northState);

        tractor.moveForward();
        assertThat(tractor.getPosition()).isEqualTo(new Position(1, 2));
        assertThat(tractor.getOrientation()).isEqualTo("N");
        assertThat(tractor.getOrientationState()).isEqualTo(northState);

        tractor.moveForward();
        assertThat(tractor.getPosition()).isEqualTo(new Position(1, 3));
        assertThat(tractor.getOrientation()).isEqualTo("N");
        assertThat(tractor.getOrientationState()).isEqualTo(northState);
    }
}
