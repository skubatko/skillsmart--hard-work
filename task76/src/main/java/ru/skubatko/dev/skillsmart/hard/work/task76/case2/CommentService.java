package ru.skubatko.dev.skillsmart.hard.work.task76.case2;

public interface CommentService {

    /**
     * Спецификация
     * Создаёт комментарий к заявке
     */
    // Предусловие: {
    // applicationId - число,
    // comment - данные комментария
    // }
    CommentDTO create(Long applicationId, CommentInputDTO comment);
    // Постусловие: {
    // возвращаемое значение - это данные сохранённого комментария к заявке
    // }
}
