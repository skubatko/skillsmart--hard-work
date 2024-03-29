# Задание 67

## Отчёт

### Проект 1

Сервис Витрина Заявок.

Код достаточно хорошо структурирован. Выделены три больших модуля.

Архитектурно код выдержан более-менее в стандартном микросервисном стиле, привычном для построения Spring Boot REST
приложений. Поддержка такого кода достаточно проста.

Специфика построения микросервиса предполагает проведение изменений, как правило, на нескольких слоях приложения.

Логика поиска заявок непрозрачна и постоянно подвергается обновлению из-за некорректной работы.

В целом код данного сервиса соответствует идеалу примерно на 40%.

### Проект 2

Сервис управления заказами SIM карт.

Код структурирован по стандартам построения микросервисов, принятых в команде.

Архитектура кода соответствует принятым в команде нормам. Поддержка такого кода достаточно проста.

Изменения, как правило, включают добавление логики на нескольких слоях приложения.

Логика доставки карт требует доработки, поскольку изначально не было хорошей аналитик по данной теме. Для кодирования
использован Kotlin, что заметно улучшает устойчивость кода к сбоям.

В целом код данного сервиса соответствует идеалу примерно на 60%.

### Проект 3

Вычисление затронутых API в Java приложениях.

Приложение разделено на чёткие логические блоки.

Код использует несколько известных паттернов проектирования, что может затруднить его модификацию для непосвящённого
программиста.

Добавление новой логики достаточно лёгкое с учётом переиспользования внедрённых паттернов.

Основная логика обхода AST достаточно сложна, использована рекурсия.

В целом код данного сервиса соответствует идеалу примерно на 50%.
