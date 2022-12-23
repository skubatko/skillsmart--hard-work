package ru.skubatko.dev.skillsmart.hard.work.task18.imprecision.case1;

public interface ReferenceGroupRepositoryRefactored {

    // передача одного идентификатора слишком сужает логику
    // изменили на передачу списка идентификаторов,
    // что позволит проводить удаление более гибко
    void delete(Iterable<Long> ids);
}
