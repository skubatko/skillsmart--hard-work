# Задание 30

## Решение

### case 1

Формирование клиентского mdmId.

```java
// неименованный кусок кода
clientMdmId=applicantObj.getMdmId();
```

### case 2

Формирование полного имени клиента.

```java
// неименованный кусок кода
clientFullName=clientSurname+clientName+clientMiddleName;
```

### case 3

Формирование дня рождения клиента.

```java
// неименованный кусок кода
clientBirthDate=applicantObj.getBirthDate();
```

### case 4

Формирование адреса заявителя.

```java
// неименованный кусок кода
if(nonNull(applicantObj.getAddresses())){
    applicant.setAddresses(collectionMapper(applicantObj.getAddresses(),addr->{
    ApplicantAddressEntity address=applicantMapper.toAddressEntity(addr);
    address.setApplicant(applicant);
    return address;
    }));
    }
```

### case 5

Формирование дохода заявителя.

```java
// неименованный кусок кода
if(nonNull(applicantObj.getIncomes())){
    applicant.setIncomes(collectionMapper(applicantObj.getIncomes(),income->{
    ApplicantIncomeEntity incomeEntity=applicantMapper.toIncomeEntity(income);
    incomeEntity.setApplicant(applicant);
    return incomeEntity;
    }));
    }
```
