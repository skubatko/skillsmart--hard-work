# Задание 15

## Решение

- [case1](case1)
- [case2](case2)
- [case3](case3)
- [case4](case4)
- [case5](case5)

## Отчёт

### case1

- `Long id` заменён на `CarId` c инкапсулированным валидированным `id`
- убрана проверка на null
- тем самым мы застрахованы на более высоком уровне

### case2

- параметры метода `findAll` завёрнуты в DTO, где гарантировано инкапсулировано ненулевое состояние
- тем самым убрали возможные ошибочные ситуации и проверки на null

### case3

- гарантия отсутствия null в `PersonDto` и `Vehicle` позволила убрать проверки на потенциально ошибочные ситуации

### case4

- замена `id` на тип позволила избежать потенциальных ошибок

### case5

- заменён `UUID id` на `RefItemId`, что повысило уровень абстракции и устранило потенциальные ошибки