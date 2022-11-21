# Задание 10

## Решение

### case 1

было:

```java
outboxFeature.transmitter.transmit(
    entity.getApplicationId(),
    objectMapper.readValue(entity.getPayload(),
    outboxFeature.getToken()));
```

стало:

```java
String applicationId=entity.getApplicationId();
    String payload=entity.getPayload();
    Class token=outboxFeature.getToken();
    Object entityValue=objectMapper.readValue(payload,token);
    outboxFeature.transmitter.transmit(applicationId,entityValue);
```

### case 2

было:

```java
if(nonNull(incomingConfigProps.getPartitions())&&Arrays.stream(incomingConfigProps.getPartitions()).anyMatch(part->0==part)){}
```

стало:

```java
int[]partitions=incomingConfigProps.getPartitions();
    boolean containsPartZero=Arrays.stream(partitions).anyMatch(part->0==part);
    if(nonNull(partitions)&&containsPartZero){
```

### case 3

было:

```java
return isNull(application.getCreditParam().getHandAmount())
    ?application.getCreditParam().getTotalAmount().toString()
    :application.getCreditParam().getHandAmount().toString();
```

стало:

```java
CreditParam creditParam=application.getCreditParam();
    BigDecimal handAmount=creditParam.getHandAmount();
    BigDecimal totalAmount=creditParam.getTotalAmount();
    return isNull(handAmount)?totalAmount.toString():handAmount.toString();
```

### case 4

было:

```java
int endIndex=ex.getMessage().indexOf("JSON {");
    log.debug(ex.getMessage().substring(0,endIndex==-1?ex.getMessage().length():endIndex));
```

стало:

```java
String message=ex.getMessage();
    int jsonIndex=message.indexOf("JSON {");
    int messageLength=message.length();
    int endIndex=jsonIndex==-1?messageLength:jsonIndex;
    log.debug(message.substring(0,endIndex));
```

### case 5

было:

```java
if(nonNullAndNonEmpty(unifiedApplication.getCarInfo())
    &&nonNull(unifiedApplication.getCarInfo().get(0).getCarMark())){
```

стало:

```java
List<CarInfo> applicationCarInfo=unifiedApplication.getCarInfo();
    if(nonNullAndNonEmpty(applicationCarInfo)){
    String carMark=applicationCarInfo.get(0).getCarMark();
    if(nonNull(carMark)){
```
