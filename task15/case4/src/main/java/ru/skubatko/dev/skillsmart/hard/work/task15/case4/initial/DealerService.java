package ru.skubatko.dev.skillsmart.hard.work.task15.case4.initial;

import ru.skubatko.dev.skillsmart.hard.work.task15.case4.common.DealerDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DealerService {
    private final DealerRepository repository;

    public DealerDto getOne(String id) {
        if (id == null) {
            return null;
        }

        return repository.findById(id);
    }

    public void delete(String id) {
        if (id == null) {
            return;
        }

        repository.delete(id);
    }
}
