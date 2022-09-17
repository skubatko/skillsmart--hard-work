package ru.skubatko.dev.skillsmart;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TractorServiceTurnClockwise implements TractorMovable {
    private final Tractor tractor;

    @Override
    public void move() {
        switch (tractor.orientation()) {
            case "N":
                tractor.orientation("E");
                break;
            case "E":
                tractor.orientation("S");
                break;
            case "S":
                tractor.orientation("W");
                break;
            case "W":
                tractor.orientation("N");
                break;
        }
    }

    @Override
    public boolean isApplicable(TractorCommand command) {
        return command == TractorCommand.TURN;
    }
}
