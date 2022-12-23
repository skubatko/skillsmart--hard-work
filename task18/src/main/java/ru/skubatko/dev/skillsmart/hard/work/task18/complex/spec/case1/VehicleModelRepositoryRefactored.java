package ru.skubatko.dev.skillsmart.hard.work.task18.complex.spec.case1;

import java.util.Optional;

public interface VehicleModelRepositoryRefactored extends PagingAndSortingRepository<VehicleModelEntity, Long> {

    Optional<VehicleModelEntity> find(Long id);

    VehicleModelEntity create(VehicleModelCreateDto dto);

    void update(Long id, VehicleModelUpdateDto dto);

    void delete(Long id);
}
