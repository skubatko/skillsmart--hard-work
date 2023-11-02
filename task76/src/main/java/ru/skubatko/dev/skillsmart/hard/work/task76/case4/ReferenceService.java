package ru.skubatko.dev.skillsmart.hard.work.task76.case4;

import java.util.List;

public interface ReferenceService {

    /**
     * Спецификация
     * Находит все ссылки по ключу
     */
    // Предусловие: { key - ключ }
    List<String> findAllStates(String key);
    // Постусловие: {
    // Если ссылки найдены,
    // то возвращаемое значение - это список ссылок
    // Иначе
    // возвращаемое значение - это пустой список
    // }
}
