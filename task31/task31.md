# Задание 31

## Отчёт

### case 1

было:

```java
public ApplicationDTO findByApplicationId(String xEmployeePIN,Long applicationId,boolean mapped){}
```

стало:

```java
public <T extends Application> T findById(Long applicationId, ApplicationDetails<T> details){}
```

Метод `findById` стал полиморфным. Теперь он может обрабатывать любые категории заявок.

### case 2

было:

```java
CommentDTO modify(String xEmployeePIN,String xEmployeeLogin,Long applicationId,Long commentId,CommentInputDTO comment);
```

стало:

```java
<T extends Comment> modify(Long commentId, T comment, CommentDetails<T> details);
```

Метод `modify` стал полиморфным. Теперь можно обрабатывать любые комментарии.

### case 3

было:

```java
String getLocalizedMessage(String key);
```

стало:

```java
<K extends Key, M extends Message> Optional<M> getMessage(K key);
```

Метод `getMessage` стал полиморфным. Теперь можно получать различные типы сообщений по ключу.

### case 4

было:

```java
List<String> findAllStates(String xEmployeePIN);
```

стало:

```java
<T extends State> List<T> findAllStates(String key);
```

Метод `findAllStates` стал полиморфным. Теперь можно получать различные состояния.

### case 5

было:

```java
UserDetailsDTO create(String xEmployeePIN, Long applicationId);
```

стало:

```java
<T extends User> T create(String key, Long applicationId, T user);
```

Метод `create` стал полиморфным. Теперь он позволяет создать пользователя любого типа.

