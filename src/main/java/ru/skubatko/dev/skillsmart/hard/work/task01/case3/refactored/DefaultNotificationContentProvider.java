package ru.skubatko.dev.skillsmart.hard.work.task01.case3.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task01.case3.dependency.ContactPhoneCommunicationDto;
import ru.skubatko.dev.skillsmart.hard.work.task01.case3.dependency.DefaultHtmlTableCreator;
import ru.skubatko.dev.skillsmart.hard.work.task01.case3.dependency.HtmlTableRawProvider;
import ru.skubatko.dev.skillsmart.hard.work.task01.case3.dependency.InvalidDataException;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultNotificationContentProvider {
    private final DefaultHtmlTableCreator defaultHtmlTableCreator;

    private final static String HEADER_TEXT = "Поступила заявка на выкуп автомобиля клиента ВТБ.</br>Необходимо связаться с клиентом в течение 1 часа.";

    public String getNotificationMessage(
        @NonNull Vehicle vehicle,
        @NonNull PersonDto clientInfo,
        @NonNull String mdmId
    ) throws InvalidDataException {
        String clientPhones = clientInfo.getContactPhoneCommunication().stream()
            .map(ContactPhoneCommunicationDto::getCompleteNumber)
            .collect(Collectors.joining(", "));

        StringBuilder clientFullName = new StringBuilder();
        clientFullName.append(clientInfo.getLastName()).append(" ");
        clientFullName.append(clientInfo.getFirstName()).append(" ");
        clientFullName.append(clientInfo.getMiddleName());

        HtmlTableRawProvider clientTable = defaultHtmlTableCreator.createTable();
        clientTable.addRow().addHeaderColumn("ФИО").addColumn(clientFullName.toString());
        clientTable.addRow().addHeaderColumn("MDM ID").addColumn(mdmId);
        clientTable.addRow().addHeaderColumn("Дата рожд.").addColumn(clientInfo.getBirthDateTime().toLocalDate().toString());
        clientTable.addRow().addHeaderColumn("Тел.").addColumn(clientPhones);

        String personInfo = clientTable.get();

        HtmlTableRawProvider vehicleTable = defaultHtmlTableCreator.createTable();
        vehicleTable.addRow().addHeaderColumn("VIN").addColumn(vehicle.getVin());
        vehicleTable.addRow().addHeaderColumn("Марка").addColumn(vehicle.getBrand());
        vehicleTable.addRow().addHeaderColumn("Модель").addColumn(vehicle.getModel());
        vehicleTable.addRow().addHeaderColumn("Гос-Номер").addColumn(vehicle.getRegNumber());
        vehicleTable.addRow().addHeaderColumn("Год").addColumn(vehicle.getManufactureYear().toString());
        vehicleTable.addRow().addHeaderColumn("Объем двиг").addColumn(vehicle.getEngineVolume().toString());
        vehicleTable.addRow().addHeaderColumn("Мощность").addColumn(vehicle.getEnginePower().toString());

        String vehicleInfo = vehicleTable.get();

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
        private List<ContactPhoneCommunicationDto> contactPhoneCommunication = Collections.emptyList();
        private String lastName = "";
        private String firstName = "";
        private String middleName = "";
        private Date birthDateTime = Date.valueOf(LocalDate.now());
    }

    @Data
    public class Vehicle {
        private String vin = "";
        private String brand = "";
        private String model = "";
        private String regNumber = "";
        private Object manufactureYear = new Object();
        private Object engineVolume = new Object();
        private Object enginePower = new Object();
    }
}
