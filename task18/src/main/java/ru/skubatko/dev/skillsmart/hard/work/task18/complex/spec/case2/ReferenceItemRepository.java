package ru.skubatko.dev.skillsmart.hard.work.task18.complex.spec.case2;

import java.util.Optional;

public class ReferenceItemRepository extends PagingAndSortingRepository {

    Optional find(Long id);

    Object create(CreateDto dto);

    void update(Long id, UpdateDto dto);

    void delete(Long id);
}
