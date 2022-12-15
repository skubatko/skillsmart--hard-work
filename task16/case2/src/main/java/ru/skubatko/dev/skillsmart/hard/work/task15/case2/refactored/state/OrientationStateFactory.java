package ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.state;

import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.Tractor;
import ru.skubatko.dev.skillsmart.hard.work.task15.case2.refactored.dictionary.Orientation;

import java.util.HashMap;
import java.util.Map;

public class OrientationStateFactory {
    private static final Map<Tractor, Map<Orientation, OrientationState>> CACHE = new HashMap<>();

    private OrientationStateFactory() {}

    public static OrientationState build(Tractor tractor, Orientation orientation) {
        switch (orientation) {
            case NORTH:
                return CACHE
                    .computeIfAbsent(tractor, OrientationStateFactory::newMap)
                    .computeIfAbsent(orientation, k -> new NorthState(tractor));
            case EAST:
                return CACHE
                    .computeIfAbsent(tractor, OrientationStateFactory::newMap)
                    .computeIfAbsent(orientation, k -> new EastState(tractor));
            case SOUTH:
                return CACHE
                    .computeIfAbsent(tractor, OrientationStateFactory::newMap)
                    .computeIfAbsent(orientation, k -> new SouthState(tractor));
            case WEST:
                return CACHE
                    .computeIfAbsent(tractor, OrientationStateFactory::newMap)
                    .computeIfAbsent(orientation, k -> new WestState(tractor));
            default:
                throw new IllegalArgumentException("No such state available");
        }
    }

    private static Map<Orientation, OrientationState> newMap(Tractor tractor) {return new HashMap<>();}
}
