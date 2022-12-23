package ru.skubatko.dev.skillsmart.hard.work.task18.complex.spec.case1;

import java.util.Optional;

public interface VehicleModelRepository extends PagingAndSortingRepository {

    Optional find(Long id);

    Object create(CreateDto dto);

    void update(Long id, UpdateDto dto);

    void delete(Long id);
}
