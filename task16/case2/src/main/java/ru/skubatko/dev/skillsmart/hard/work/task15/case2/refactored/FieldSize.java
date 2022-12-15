package ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored;

public class FieldSize {
    private final int weight;
    private final int height;

    public FieldSize(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof FieldSize)) {return false;}

        FieldSize fieldSize = (FieldSize) o;

        if (weight != fieldSize.weight) {return false;}
        return height == fieldSize.height;
    }

    @Override
    public int hashCode() {
        int result = weight;
        result = 31 * result + height;
        return result;
    }
}
