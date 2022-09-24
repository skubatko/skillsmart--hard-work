package ru.skubatko.dev.skillsmart.hard.work.task01.case1.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.ClientInfoIntegrationClient;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.ClientInfoIntegrationProperties;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.ClientNotificationContentProvider;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.EntityNotFoundException;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.GarageException;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.IntegrationException;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.InvalidDataException;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.NotificationFromPropertiesMetaData;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.NotificationService;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.PersonDto;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.SellCarRequest;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.SellCarRequestService;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.SellProperties;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.Vehicle;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.VehicleNotFoundException;
import ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency.VehicleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SellCarServiceImpl {
    private final SellCarRequestService sellCarRequestService;
    private final VehicleRepository vehicleRepository;
    private final NotificationService notificationService;
    private final ClientInfoIntegrationClient<PersonDto> clientInfoRestTemplateIntegrationClient;
    private final ClientInfoIntegrationClient<PersonDto> ownrBffClientInfoIntegrationClient;
    private final SellProperties sellProperties;
    private final ClientInfoIntegrationProperties clientInfoIntegrationProperties;
    private final ClientNotificationContentProvider notificationContentProvider;

    private static final String SUBJECT_TEXT = "SBJ";
    private static final String INFO_KEY = "cell-my-car";

    public Integer createNewRequest(String id, int vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new VehicleNotFoundException(
                String.format("Vehicle id= %s, userId= %s wasn't found", vehicleId, id)));
        SellCarRequest sellRequest = sellCarRequestService.createRequest(vehicle);
        String notificationMessage = getNotificationMessage(id, vehicle, sellRequest);
        NotificationFromPropertiesMetaData metaData = getNotificationFromPropertiesMetaData(notificationMessage);
        notify(sellRequest, metaData);
        return sellRequest.getId();
    }

    private String getNotificationMessage(String id, Vehicle vehicle, SellCarRequest sellRequest) {
        try {
            PersonDto clientInfo = getClientInfo(id);
            return notificationContentProvider.getNotificationMessage(vehicle, clientInfo, id);
        } catch (InvalidDataException ex) {
            String msg = ex.getMessage();
            cancelRequest(sellRequest, msg);
            throw new GarageException(msg);
        }
    }

    private PersonDto getClientInfo(String id) {
        try {
            return clientInfoIntegrationProperties.isUseBff()
                ? ownrBffClientInfoIntegrationClient.getClientInfo(id)
                : clientInfoRestTemplateIntegrationClient.getClientInfo(id);
        } catch (IntegrationException ex) {
            throw getPersonServiceFailException(ex);
        }
    }

    private RuntimeException getPersonServiceFailException(IntegrationException ex) {
        return sellProperties.isIgnorePersonServiceFail()
            ? new RuntimeException(ex)
            : new GarageException(ex.getMessage());
    }

    private NotificationFromPropertiesMetaData getNotificationFromPropertiesMetaData(String notificationMessage) {
        NotificationFromPropertiesMetaData metaData = new NotificationFromPropertiesMetaData();
        metaData.setText(notificationMessage);
        metaData.setSubject(SUBJECT_TEXT);
        metaData.setInfoKey(INFO_KEY);
        metaData.setHtml(true);
        return metaData;
    }

    private void notify(SellCarRequest sellRequest, NotificationFromPropertiesMetaData metaData) {
        try {
            notificationService.sendNotification(metaData);
        } catch (InvalidDataException | IntegrationException ex) {
            String msg = String.format("canceled because of integration error %s", ex.getMessage());
            cancelRequest(sellRequest, msg);
            throw new GarageException(msg);
        }
    }

    private void cancelRequest(SellCarRequest sellRequest, String msg) {
        try {
            sellCarRequestService.cancelRequest(sellRequest, msg);
        } catch (EntityNotFoundException ex) {
            throw new GarageException(msg);
        }
    }
}
