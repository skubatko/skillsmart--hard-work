# Задание 2

## Задача

Фазз-тестирование.

Примените для одного из своих проектов фазз-тестирование.

Немного рассказывал о нём тут:

- https://vk.com/wall-152484379_3530
- https://vk.com/wall-152484379_3532

Используйте одну из следующих систем, на выбор (или любую другую):

- [afl++](github.com/AFLplusplus/AFLplusplus)
- [libfuzzer](llvm.org/docs/LibFuzzer.html)
- [ClusterFuzz](github.com/google/clusterfuzz)

Быстрое введение в тему:

- habr.com/ru/company/bizone/blog/570312/
- habr.com/ru/company/dsec/blog/435644/

В отчёте напишите, какие трудности были при организации фазз-тестирования, на каком языке написан ваш проект, сколько
в нём строк кода, и сколько ошибок в нём выявило фазз-тестирование.

## Решение

Тестировался код, написанный на Java.

Основные трудности

- Отсутствие внятной документации. Видно, что инициатива находится пока в зачаточном состоянии
- Google предлагает развёртывание дополнительной инфраструктуры, что имеет смысл делать при целевом использовании данной
  технологии

### Javafuzz

https://github.com/fuzzitdev/javafuzz
https://gitlab.com/gitlab-org/security-products/analyzers/fuzzers/javafuzz

Запуск:

```bash
mvn clean install
MAVEN_OPTS="-javaagent:lib/jacocoagent.jar" mvn javafuzz:fuzz
```

- опробован тестовый пример, ошибка найдена "java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length
  3"
- как расширить практику на свой код осталось непонятным, на вход возможно принять только массив байт

### JQF

https://github.com/rohanpadhye/jqf

Запуск при помощи JQF Maven Plugin:

```bash
mvn jqf:fuzz
```

- опробованы тестовые примеры
- протестирован ExcelTask, 110 LOC
- найдено 2 ошибки "Found crash: class java.lang.IllegalArgumentException - wrong num"
