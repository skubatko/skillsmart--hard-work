# Задание 65

## Отчёт

### [case1](case1)

ApplicationEntity -> lift -> Application
Application -> lower -> ApplicationReportTO

ApplicationEntity, ApplicationReportTO используются для вычислительной обработки.
Application используется для решения изначально поставленных задач.

### [case2](case2)

CarRequestTO -> lift -> Car
Car -> lower -> CarEntity

CarEntity, CarRequestTO используются для вычислительной обработки.
Car используется для решения изначально поставленных задач.

### [case3](case3)

ApplicantCreateRequestTO -> lift -> Applicant
Applicant -> lower -> ApplicantMessageTO

ApplicantCreateRequestTO, ApplicantMessageTO используются для вычислительной обработки.
Applicant используется для решения изначально поставленных задач.
