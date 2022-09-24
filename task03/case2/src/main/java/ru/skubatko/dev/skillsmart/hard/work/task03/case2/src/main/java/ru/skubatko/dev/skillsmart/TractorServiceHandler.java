package ru.skubatko.dev.skillsmart.hard.work.task03.case2.src.main.java.ru.skubatko.dev.skillsmart;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TractorServiceHandler {
    private final List<TractorMovable> tractorMovableList;

    public void handle(TractorCommand command) {
        tractorMovableList.forEach(movable -> {
            if (movable.isApplicable(command)) {
                movable.move();
            }
        });
    }
}
