package ru.skubatko.dev.skillsmart.hard.work.task01.case3.initial;

import ru.skubatko.dev.skillsmart.hard.work.task01.case3.dependency.ContactPhoneCommunicationDto;
import ru.skubatko.dev.skillsmart.hard.work.task01.case3.dependency.DefaultHtmlTableCreator;
import ru.skubatko.dev.skillsmart.hard.work.task01.case3.dependency.HtmlTableRawProvider;
import ru.skubatko.dev.skillsmart.hard.work.task01.case3.dependency.InvalidDataException;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultNotificationContentProvider {
    private final DefaultHtmlTableCreator defaultHtmlTableCreator;

    private final static String HEADER_TEXT = "Поступила заявка на выкуп автомобиля клиента ВТБ.</br>Необходимо связаться с клиентом в течение 1 часа.";

    public String getNotificationMessage(Vehicle vehicle, PersonDto clientInfo, String mdmId) throws InvalidDataException {
        if (Objects.isNull(mdmId)) {throw new InvalidDataException("user id is not present");}

        List<ContactPhoneCommunicationDto> contactPhoneCommunicationDto = new ArrayList<>();
        if (clientInfo != null && clientInfo.getContactPhoneCommunication() != null) {
            contactPhoneCommunicationDto = clientInfo.getContactPhoneCommunication();
        }

        String clientPhones = contactPhoneCommunicationDto.stream().map(ContactPhoneCommunicationDto::getCompleteNumber).collect(Collectors.joining(", "));
        String personInfo;
        if (clientInfo != null) {

            StringBuilder clientFullName = new StringBuilder();
            if (clientInfo.getLastName() != null) {clientFullName.append(clientInfo.getLastName()).append(" ");}
            if (clientInfo.getFirstName() != null) {clientFullName.append(clientInfo.getFirstName()).append(" ");}
            if (clientInfo.getMiddleName() != null) {clientFullName.append(clientInfo.getMiddleName());}

            HtmlTableRawProvider clientTable = defaultHtmlTableCreator.createTable();
            if (!clientFullName.toString().isEmpty()) {
                clientTable.addRow().addHeaderColumn("ФИО").addColumn(clientFullName.toString());
            }

            clientTable.addRow().addHeaderColumn("MDM ID").addColumn(mdmId);
            if (clientInfo.getBirthDateTime() != null) {
                clientTable.addRow().addHeaderColumn("Дата рожд.").addColumn(clientInfo.getBirthDateTime().toLocalDate().toString());
            }
            if (clientPhones.isEmpty()) {throw new InvalidDataException("client phone is empty but it's required");}
            clientTable.addRow().addHeaderColumn("Тел.").addColumn(clientPhones);

            personInfo = clientTable.get();

        } else {
            personInfo = defaultHtmlTableCreator.createTable().addRow().addHeaderColumn("mdmId").addColumn(mdmId);
        }

        HtmlTableRawProvider vehicleTable = defaultHtmlTableCreator.createTable();
        String vehicleInfo;
        if (Objects.isNull(vehicle.getVin())) {throw new InvalidDataException("vehicle VIN is not present");}
        vehicleTable.addRow().addHeaderColumn("VIN").addColumn(vehicle.getVin());

        if (Objects.isNull(vehicle.getBrand())) {throw new InvalidDataException("vehicle brand is not present");}
        vehicleTable.addRow().addHeaderColumn("Марка").addColumn(vehicle.getBrand());

        if (Objects.isNull(vehicle.getModel())) {throw new InvalidDataException("vehicle model is not present");}
        vehicleTable.addRow().addHeaderColumn("Модель").addColumn(vehicle.getModel());

        if (Objects.isNull(vehicle.getRegNumber())) {
            throw new InvalidDataException("vehicle reg number is not present");
        }
        vehicleTable.addRow().addHeaderColumn("Гос-Номер").addColumn(vehicle.getRegNumber());

        if (vehicle.getManufactureYear() == null) {
            throw new InvalidDataException("vehicle manufacture year is not present");
        }
        vehicleTable.addRow().addHeaderColumn("Год").addColumn(vehicle.getManufactureYear().toString());

        if (vehicle.getEngineVolume() != null) {
            vehicleTable.addRow().addHeaderColumn("Объем двиг").addColumn(vehicle.getEngineVolume().toString());
        }
        if (vehicle.getEnginePower() != null) {
            vehicleTable.addRow().addHeaderColumn("Мощность").addColumn(vehicle.getEnginePower().toString());
        }

        vehicleInfo = vehicleTable.get();

        return new StringBuilder()
            .append(HEADER_TEXT).append("</br></br>")
            .append("Клиент:").append("</br>")
            .append(personInfo).append("</br></br>")
            .append("Авто:").append("</br>")
            .append(vehicleInfo)
            .toString();
    }

    @Data
    private class PersonDto {
        private List<ContactPhoneCommunicationDto> contactPhoneCommunication;
        private String lastName;
        private String firstName;
        private String middleName;
        private Date birthDateTime;
    }

    @Data
    public class Vehicle {
        private String vin;
        private String brand;
        private String model;
        private String regNumber;
        private Object manufactureYear;
        private Object engineVolume;
        private Object enginePower;
    }
}
