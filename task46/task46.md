# Задание 46

## Решение

- [case1](case1)
- [case2](case2)
- [case3](case3)
- [case4](case4)
- [case5](case5)

## Отчёт

### case1

Применён паттерн Factory. Для каждой операции создан свой класс. Фабрика содержит метод, возвращающий объект нужной
операции.

### case2

Использовано перечисление с нужными операциями.

### case3

Рефакторинг выполнен при помощи паттерна Command. Подготовлены команды для каждой операции.

### case4

Использован полиморфизм. Для каждого вида птицы создан свой класс с нужными свойствами.

### case5

Использованы паттерны стратегия и фабрика. Для каждого действия определяется своя стратегия. При помощи фабрики получаем
нужную стратегию.
