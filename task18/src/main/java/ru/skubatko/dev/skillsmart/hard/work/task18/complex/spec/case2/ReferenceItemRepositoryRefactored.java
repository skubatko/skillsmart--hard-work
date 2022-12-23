package ru.skubatko.dev.skillsmart.hard.work.task18.complex.spec.case2;

import java.util.Optional;

public interface ReferenceItemRepositoryRefactored extends PagingAndSortingRepository<ReferenceItemEntity, Long> {

    Optional<ReferenceItemEntity> find(Long id);

    ReferenceItemEntity create(ReferenceItemCreateDto dto);

    void update(Long id, ReferenceItemUpdateDto dto);

    void delete(Long id);
}
