package ru.skubatko.dev.skillsmart.hard.work.task15.case3.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task15.case3.common.ContactPhoneCommunicationDto;
import ru.skubatko.dev.skillsmart.hard.work.task15.case3.common.DefaultHtmlTableCreator;
import ru.skubatko.dev.skillsmart.hard.work.task15.case3.common.HtmlTableRawProvider;
import ru.skubatko.dev.skillsmart.hard.work.task15.case3.common.InvalidDataException;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultNotificationContentProviderRefactored {
    private final DefaultHtmlTableCreator defaultHtmlTableCreator;

    private final static String HEADER_TEXT = "Поступила заявка на выкуп автомобиля клиента ВТБ.</br>Необходимо связаться с клиентом в течение 1 часа.";

    public String getNotificationMessage(@NonNull Vehicle vehicle, @NonNull PersonDto clientInfo, @NonNull String mdmId) throws InvalidDataException {
        String clientPhones =
            clientInfo.getContactPhoneCommunication().stream()
                .map(ContactPhoneCommunicationDto::getCompleteNumber)
                .collect(Collectors.joining(", "));

        String personInfo;
        if (!clientInfo.equals(PersonDto.NONE)) {
            StringBuilder clientFullName = new StringBuilder();
            clientFullName.append(clientInfo.getLastName()).append(" ");
            clientFullName.append(clientInfo.getFirstName()).append(" ");
            clientFullName.append(clientInfo.getMiddleName());

            HtmlTableRawProvider clientTable = defaultHtmlTableCreator.createTable();
            if (!clientFullName.toString().isEmpty()) {
                clientTable.addRow().addHeaderColumn("ФИО").addColumn(clientFullName.toString());
            }

            clientTable.addRow().addHeaderColumn("MDM ID").addColumn(mdmId);
            clientTable.addRow().addHeaderColumn("Дата рожд.").addColumn(clientInfo.getBirthDateTime().toString());
            if (clientPhones.isEmpty()) {throw new InvalidDataException("client phone is empty but it's required");}
            clientTable.addRow().addHeaderColumn("Тел.").addColumn(clientPhones);
            personInfo = clientTable.get();

        } else {
            personInfo = defaultHtmlTableCreator.createTable().addRow().addHeaderColumn("mdmId").addColumn(mdmId);
        }

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

    @Getter
    private static class PersonDto {
        @NonNull private List<ContactPhoneCommunicationDto> contactPhoneCommunication;
        @NonNull private String lastName;
        @NonNull private String firstName;
        @NonNull private String middleName;
        @NonNull private Date birthDateTime;

        public static final PersonDto NONE = new PersonDto();
    }

    @Getter
    private static class Vehicle {
        @NonNull private String vin;
        @NonNull private String brand;
        @NonNull private String model;
        @NonNull private String regNumber;
        @NonNull private Object manufactureYear;
        @NonNull private Object engineVolume;
        @NonNull private Object enginePower;
    }
}
