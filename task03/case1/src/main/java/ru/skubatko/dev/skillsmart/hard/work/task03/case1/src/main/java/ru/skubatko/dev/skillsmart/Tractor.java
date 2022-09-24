package ru.skubatko.dev.skillsmart;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Tractor {
    private int[] position = new int[]{0, 0};
    private int[] field = new int[]{5, 5};
    private String orientation = "N";
}
