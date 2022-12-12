package ru.skubatko.dev.skillsmart.hard.work.task15.case5.initial;

import ru.skubatko.dev.skillsmart.hard.work.task15.case5.common.RefItemDto;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class RefItemService {
    private final RefItemRepository repository;

    public RefItemDto get(UUID id) {
        if (id == null) {
            return null;
        }

        return repository.findById(id);
    }

    public void delete(UUID id) {
        if (id == null) {
            return;
        }

        repository.delete(id);
    }
}
