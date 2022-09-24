# Задание 4

## Задача

1. Изучите материал из СильныхИдей "Три уровня рассуждений о программной системе - 3".

2. Выберите в вашем рабочем проекте некоторый "кусок кода" (несколько сотен строк), и сформулируйте словесно его
   логический дизайн.
    - Насколько существующий код ему реально соответствует?
    - Может быть, теперь даже напрашивается совершенно другой код?

3. Реализуйте этот дизайн (перепишите существующий код) ...
    - в схеме "целиком или ничего", или (лучше)
    - в декларативном виде, насколько возможно.

Старайтесь при этом достигать максимальной ясности и соответствия кода 1:1 с дизайном.
1:1 означает, напомню, что код не просто прямо реализует дизайн, но и не возможен никакой другой код, который это делает
как-то по другому. То есть и дизайн напрямую следует из кода.

Но ни в коем случае не усложняйте! :) А то идея "декларативщины" подчас провоцирует на ужасные "универсальные
оптимизированные" конструкции.

4. Повторите пункты 2 и 3 ещё 2-3 раза с другим кодом.

В решении отправляете (по каждой итерации) исходный код, рефлексию п.2 и новую версию п.3. Сколько времени занимает
каждая такая итерация?

---

В каждом отчёте по этому и всем последующим заданиям обязательно должны быть ваши рассуждения и рефлексия по теме в
объёме не менее 12 фраз, а не просто "сдал задачу и забыл".

Вы получите максимальные баллы только за качественный и подробный отчёт.

Нередкая ошибка, за которую золото не начисляется, а списывается, это отчёт "для галочки", на "поскорее отвязаться".

---

## Решение

- [case1 report](case1/report.md)
- [case2 report](case2/report.md)
- [case3 report](case2/report.md)

Основной приём для написания нового кода схож c TDD за исключением шага тестов, вместо которого использован выделенный
дизайн. Также применены функциональное программирование, иммутабельные дата классы и принципы Object Calisthenics.