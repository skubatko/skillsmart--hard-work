# Задание 77

## Отчёт

### case1

Наиболее часто такие проблемы в моей практике встречались на стыке бэкенда и фронтенда.
К примеру самая частая проблема с форматами дат. До тех пор, пока пользователь не выскажет требование о необходимости
предоставлять ему дату в конкретном виде, проблем не возникает. Как только речь заходит о специфическом представлении
даты, апи ломается и возникает дилемма на чьей стороне править спецификацию.

### case2

Раньше частенько видел пренебрежение дженериками. Когда, например, в List на добавляли расшифровку типов его
содержимого. Пару раз из-за этого сам попадал в ситуации, кода код падал в рантайме, хотя по логике вроде всё было
нормально.

### case3

При контрактном подходе к формированию API на стороне бэка со стороны фронта, коллеги нередко не указывают корректно
формат передаваемых или возвращаемых данных. Самый популярный у нас пример - путаница с Long и Integer. Однажды из-за
этого мы словили кейс, где Long кастился в Integer и мы на выходк получали непредсказуемый результат.
