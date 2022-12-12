package ru.skubatko.dev.skillsmart.hard.work.task15.case1.initial;

import ru.skubatko.dev.skillsmart.hard.work.task15.case1.common.CarDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    public CarDto getCar(Long id) {
        if (id == null) {
            return null;
        }

        return repository.findById(id);
    }

    public void deleteCarById(Long id) {
        if (id == null) {
            return;
        }

        repository.deleteById(id);
    }
}
