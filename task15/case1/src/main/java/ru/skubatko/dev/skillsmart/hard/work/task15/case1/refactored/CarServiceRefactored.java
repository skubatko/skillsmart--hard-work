package ru.skubatko.dev.skillsmart.hard.work.task15.case1.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.CarDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CarServiceRefactored {
    private final CarRepositoryRefactored repository;

    public CarDto findById(CarId id) {
        return repository.findById(id);
    }

    public void delete(CarId id) {
        repository.delete(id);
    }
}
