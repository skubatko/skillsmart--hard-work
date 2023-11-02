package ru.skubatko.dev.skillsmart.hard.work.task76.case5;

public interface UserService {

    /**
     * Спецификация
     * Удаляет пользователя по его идентификатору
     */
    // Предусловие: { userId - строка }
    void delete(String userId);
    // Постусловие: {
    // Пользователь удалён
    // }
}
