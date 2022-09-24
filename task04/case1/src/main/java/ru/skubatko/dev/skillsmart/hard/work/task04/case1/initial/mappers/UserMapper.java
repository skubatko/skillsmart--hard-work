package ru.skubatko.dev.skillsmart.hard.work.task04.case1.initial.mappers;

import ru.skubatko.dev.skillsmart.hard.work.task04.case1.initial.domain.UserDetailsEntity;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.initial.dto.UserDetailsDTO;

public class UserMapper {
    public static final UserMapper INSTANCE = new UserMapper();

    public UserDetailsDTO toDto(UserDetailsEntity userDetails) {
        return null;
    }
}
