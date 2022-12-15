package ru.skubatko.dev.skillsmart.hard.work.task15.case2.initial;

public class Tractor {
    int[] position = new int[]{0, 0};
    int[] field = new int[]{5, 5};
    public String orientation = "N";


    public void moveForward() {
        if (orientation == "N") {
            position = new int[]{position[0], position[1] + 1};
        } else if (orientation == "E") {
            position = new int[]{position[0] + 1, position[1]};
        } else if (orientation == "S") {
            position = new int[]{position[0], position[1] - 1};
        } else if (orientation == "W") {
            position = new int[]{position[0] - 1, position[1]};
        }
        if (position[0] > field[0] || position[1] > field[1]) {
            throw new TractorInDitchException();
        }
    }

    public void turnClockwise() {
        if (orientation == "N") {
            orientation = "E";
        } else if (orientation == "E") {
            orientation = "S";
        } else if (orientation == "S") {
            orientation = "W";
        } else if (orientation == "W") {
            orientation = "N";
        }
    }

    public int[] getPosition() {
        return position;
    }

    public int[] getField() {
        return field;
    }

    public String getOrientation() {
        return orientation;
    }
}
