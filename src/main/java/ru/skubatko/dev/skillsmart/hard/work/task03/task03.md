# Задание 3

## Задача

Думаем на уровне дизайна

1. Напишите несколько сотен строк вашего рабочего кода по TDD. Если вдруг не знаете что это :) на курсе карьеры есть
   вводное занятие.

2. Изучите два материала из СильныхИдей "Три уровня рассуждений о программной системе" и "Три уровня рассуждений о
   программной системе - 2".

3. Повторите пункт 1 с акцентом на том, что
    - Код не должен следовать тестам, ни тесты не должны следовать коду.
    - И тесты, и код должны следовать дизайну, логической архитектуре.

Постарайтесь при этом, чтобы ваш код соответствовал ровно одному дизайну.

4. Сделайте небольшой отчёт с примерами кода из пп. 1 и 3 и вашими комментариями.

## Решение

- case1
    - содержит тесты конкретной реализации TractorServiceMoveForwardTest и TractorServiceTurnClockwiseTest
    - тесты отражают логику на уровне конкретной реализации

- case2
    - содержит тест работы системы в целом TractorServiceHandlerTest, end-to-end тест
    - тест отражают логику на уровне дизайна, проектирования системы