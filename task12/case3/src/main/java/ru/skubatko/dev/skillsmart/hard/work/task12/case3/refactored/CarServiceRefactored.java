package ru.skubatko.dev.skillsmart.hard.work.task12.case3.refactored;


import ru.skubatko.dev.skillsmart.hard.work.task12.case3.common.CarDto;

import java.util.List;

public class CarServiceRefactored {
    private final CarRepositoryRefactored repository;

    public CarServiceRefactored(CarRepositoryRefactored repository) {this.repository = repository;}

    public List<CarDto> findAll() {
        return repository.findAll();
    }

    public CarDto findById(CarId id) {
        return repository.findById(id);
    }

    public void delete(CarId id) {
        repository.delete(id);
    }
}
