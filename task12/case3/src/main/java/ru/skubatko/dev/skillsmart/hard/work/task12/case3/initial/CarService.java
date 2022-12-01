package ru.skubatko.dev.skillsmart.hard.work.task12.case3.initial;

import ru.skubatko.dev.skillsmart.hard.work.task12.case3.common.CarDto;

import java.util.List;

public class CarService {

    private CarRepository repository;

    public void setRepository(CarRepository repository) {
        this.repository = repository;
    }

    public List<CarDto> getCars() {
        return repository.findAll();
    }

    public CarDto getCar(long id) {
        return repository.findById(id);
    }

    public void deleteCarById(long id) {
        repository.deleteById(id);
    }
}
