package ru.skubatko.dev.skillsmart.hard.work.task09.refactored.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    public static String toUpper(String str) {
        return str.toUpperCase();
    }
}
