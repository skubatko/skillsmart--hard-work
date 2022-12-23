package ru.skubatko.dev.skillsmart.hard.work.task18.imprecision.case3;

public interface VehicleBrandRepositoryRefactored {

    // передача одного параметра для обновления слишком ограничивает логику
    // изменили на передачу dto, в составе которого перечислены все параметры изменения сущности,
    // теперь можно обновлять более гибко
    void update(Long id, VehicleBrandUpdateDto dto);
}
