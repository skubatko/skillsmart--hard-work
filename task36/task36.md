# Задание 36

## Отчёт

### Иерархии классов, которые можно сократить

#### case1

Сервис подготовки и сохранения отчётов.

ReportController -> ReportFileCreator -> ReportProcessor

может быть преобразован до

ReportController -> ReportProcessor

хотя с ReportFileCreator нагляднее.

#### case2

Сервис распознавания изменений в коде.

ApiService -> ApiChangesResolverService -> ResultService

может быть преобразован до

ApiService -> ApiChangesResolverService

хотя с ResultService нагляднее.

### Interface dispatch

В Java можно задать несколько интерфейсов, котоорые затем можно свободно переиспользовать:

- Eatable
- Pettable
- Uncatchable
- Thiefable
