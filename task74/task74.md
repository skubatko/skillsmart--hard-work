# Задание 74

## Отчёт

### case 1

```java
public interface CommentService {

    Iterable<CommentOutputDTO> findByParams(String xEmployeePIN, Long applicationId);

    CommentDTO create(String xEmployeePIN, String xEmployeeLogin, Long applicationId, CommentInputDTO comment);

    CommentDTO modify(String xEmployeePIN, String xEmployeeLogin, Long applicationId, Long commentId, CommentInputDTO comment);

    void delete(String xEmployeePIN, Long applicationId, Long commentId);
}
```

`CommentService` не зависит от деталей реализации, что позволяет применять его без оглядки на специфику внешнего
окружения. И, при необходимости, относительно легко менять конкретную реализацию.

### case 2

```java
public interface UserRepository extends CrudRepository<UserDetailsEntity, Long> {

    UserDetailsEntity findByUserCode(String userLogin);
}
```

`UserRepository` не зависит от конкретной СУБД. С лёгкостью можно использовать различные СУБД в разных условиях. К
примеру, можно использовать более легковесную СУБД для тестирования.

### case 3

```java
public interface ReportMapper {

    ReportDTO toDto(ReportEntity entity);

    List<ReportDTO> toDtoList(List<ReportEntity> list);
}
```

Благодаря интерфейсу `ReportMapper` можно сделать фейковую реализацию для тестирования и безболезненно тестировать
основной функционал.

### case 4

```java
public interface RefApi {

    ResponseEntity<List<String>> getCreatedChannels(String xEmployeePIN);
}
```

`RefApi` абстрагирует нас от конкретной реализации внешнего клиента. Соседняя команда может вносить изменения в
реализацию клиента, которая не требует модификации кода нашего модуля.

### case 5

```java
public interface UserService {

    UserDetailsDTO findByParams(String xEmployeePIN);

    UserDetailsDTO create(String xEmployeePIN, Long applicationId);

    void delete(String xEmployeePIN, Long applicationId);
}
```

`UserService` позволяет отделить бизнес-логику от деталей реализации инфраструктуры. Данные пользователя могут храниться
как локально, так и удалённо, в облаке. Могут использоваться различные реализации методов доступа к данным пользователя.
