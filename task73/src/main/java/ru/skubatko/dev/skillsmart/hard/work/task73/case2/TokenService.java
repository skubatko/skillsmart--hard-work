package ru.skubatko.dev.skillsmart.hard.work.task73.case2;


public interface TokenService<L> {

    Token getAuthorization(L login);
}
