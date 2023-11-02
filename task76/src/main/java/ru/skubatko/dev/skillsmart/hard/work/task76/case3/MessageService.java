package ru.skubatko.dev.skillsmart.hard.work.task76.case3;

public interface MessageService {

    /**
     * Спецификация
     * Находит сообщение по ключу
     */
    // Предусловие: { key - строка }
    String getLocalizedMessage(String key);
    // Постусловие: {
    // Если сообщение найдено,
    // то возвращаемое значение - это сообщение
    // Иначе
    // возвращаемое значение - это null
    // }
}
