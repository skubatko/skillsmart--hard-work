package ru.skubatko.dev.skillsmart.hard.work.task76.case1;

public interface ApplicationService {

    /**
     * Спецификация
     * Находит заявку по её номеру
     */
    // Предусловие: { number - строка }
    ApplicationDTO findByNumber(String number);
    // Постусловие: {
    // Если заявка найдена,
    // то возвращаемое значение - это заявка
    // Иначе
    // возвращаемое значение - это null
    // }
}
