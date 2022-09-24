package ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.service;

import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain.User;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.domain.UserCode;
import ru.skubatko.dev.skillsmart.hard.work.task04.case1.redesigned.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findByUserCode(UserCode userCode) {
        return userRepository.findByUserCode(userCode).orElse(User.NONE);
    }
}
