package ru.skubatko.dev.skillsmart.hard.work.task15.case5.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task15.case5.common.RefItemDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RefItemServiceRefactored {
    private final RefItemRepositoryRefactored repository;

    public RefItemDto get(RefItemId id) {
        return repository.findById(id);
    }

    public void delete(RefItemId id) {
        repository.delete(id);
    }
}
