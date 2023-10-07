package ru.skubatko.dev.skillsmart.hard.work.task73.case3;

public interface PersonService<ID> {

    PersonResponseDTO getPerson(ID id);
}
