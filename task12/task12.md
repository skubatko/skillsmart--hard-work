# Задание 12

## Решение

- запрещать ошибочное поведение на уровне интерфейса класса [case1](case1)
- отказаться от дефолтных конструкторов без параметров [case2](case2)
- избегать увлечения примитивными типами данных [case3](case3)

## Отчёт

### case1

- убран setter
- поле EmployeeRepository стало final
- убран конструктор по-умолчанию
- добавлен конструктор с обязательным EmployeeRepository

В итоге избежим NPE при использовании неинициализированного EmployeeRepository.

### case2

- убран конструктор по-умолчанию
- добавлен конструктор с обязательным UserRepository

### case3

- заменён примитив long на класс CarId
