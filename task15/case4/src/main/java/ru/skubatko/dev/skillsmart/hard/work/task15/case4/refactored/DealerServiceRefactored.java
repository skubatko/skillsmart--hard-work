package ru.skubatko.dev.skillsmart.hard.work.task15.case4.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task15.case4.common.DealerDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DealerServiceRefactored {
    private final DealerRepositoryRefactored repository;

    public DealerDto getOne(DealerId id) {
        return repository.findById(id);
    }

    public void delete(DealerId id) {
        repository.delete(id);
    }
}
