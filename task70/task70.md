# Задание 70

## Отчёт

### case1

Интерфейс `ApplicationService` содержит в себе контракт по работе с заявками. Имеет смысл разделить операции на группы
по сохранению, поиску и обновлению заявок. В итоге получаем набор интерфейсов со схожими по смыслу набором методов:
`PersistApplicationService`, `SearchApplicationService`, `UpdateApplicationService`.

### case2

Интерфейс `CommentService` содержит в себе контракт по работе с комментариями. Имеет смысл разделить операции на группы
по сохранению, поиску, обновлению и удалению комментариев. В итоге получаем набор семантически разделённых
интерфейсов: `DeleteCommentService`, `PersistCommentService`, `SearchCommentService`, `UpdateCommentService`.

### case3

Интерфейс `UserService` содержит в себе контракт по работе с пользователями системы. Разделим работу на группы
CRUD методов. В итоге получим набор интерфейсов: `DeleteUserService`, `PersistUserService`, `SearchUserService`.

### case4

Интерфейс `ReportService` содержит в себе контракт по работе с отчётами. Следуя ISP, можно разделить операции на группы
по сохранению, поиску и обновлению заявок. В итоге получаем следующий набор интерфейсов:`SearchReportService`,
`PersistReportService`, `DeleteReportService`.

### case5

Интерфейс `DictionaryMapperService` содержит в себе контракт по работе со словарями. Здесь смешан функционал по работе с
заявками в контексте словарей, кэширование и CRUD операции. В итоге рефакторинга, следуя ISP, получаем следующий набор
интерфейсов:`ApplicationDictionaryService`, `CacheDictionaryService`, `SearchDictionaryService`
и `UpdateDictionaryService`.
