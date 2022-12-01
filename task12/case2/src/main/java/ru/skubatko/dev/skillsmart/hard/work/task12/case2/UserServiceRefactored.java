package ru.skubatko.dev.skillsmart.hard.work.task12.case2;

import ru.skubatko.dev.skillsmart.hard.work.task12.case2.common.Id;
import ru.skubatko.dev.skillsmart.hard.work.task12.case2.common.UserDto;
import ru.skubatko.dev.skillsmart.hard.work.task12.case2.common.UserRepository;

import java.util.List;

public class UserServiceRefactored {
    private final UserRepository repository;

    public UserServiceRefactored(UserRepository repository) {this.repository = repository;}

    public List<UserDto> getUsers() {
        return repository.findAll();
    }

    public UserDto getUser(Id id) {
        return repository.findById(id);
    }
}
