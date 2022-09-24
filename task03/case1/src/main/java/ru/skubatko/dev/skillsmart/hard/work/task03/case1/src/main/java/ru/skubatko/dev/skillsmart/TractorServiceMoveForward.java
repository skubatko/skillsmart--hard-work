package ru.skubatko.dev.skillsmart;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TractorServiceMoveForward implements TractorMovable {
    private final Tractor tractor;

    @Override
    public void move() {
        switch (tractor.orientation()) {
            case "N":
                tractor.position(new int[]{tractor.position()[0], tractor.position()[1] + 1});
                break;
            case "E":
                tractor.position(new int[]{tractor.position()[0] + 1, tractor.position()[1]});
                break;
            case "S":
                tractor.position(new int[]{tractor.position()[0], tractor.position()[1] - 1});
                break;
            case "W":
                tractor.position(new int[]{tractor.position()[0] - 1, tractor.position()[1]});
                break;
        }

        if (tractor.position()[0] > tractor.field()[0] || tractor.position()[1] > tractor.field()[1]) {
            throw new TractorInDitchException();
        }
    }

    @Override
    public boolean isApplicable(TractorCommand command) {
        return command == TractorCommand.FORWARD;
    }
}
