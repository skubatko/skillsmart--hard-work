package ru.skubatko.dev.skillsmart.hard.work.task05.case1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Сервис управления продажей автомобилей обеспечивает возможность продажи автомобиля пользователем.
 * Данный сервис является одной из функций гаража.
 */
@Slf4j
@RequiredArgsConstructor
public class SellCarService {
    private static final String SUBJECT_TEXT = "SBJ";

    private final SellCarRequestService sellCarRequestService;
    private final VehicleRepository vehicleRepository;
    private final NotificationService notificationService;
    private final ClientInfoIntegrationClient<PersonDto> clientInfoRestTemplateIntegrationClient;
    private final ClientInfoIntegrationClient<PersonDto> ownrBffClientInfoIntegrationClient;
    private final SellProperties sellProperties;
    private final ClientInfoIntegrationProperties clientInfoIntegrationProperties;
    private final ClientNotificationContentProvider notificationContentProvider;

    public Integer createNewRequest(String id, int vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new VehicleNotFoundException(String.format("Vehicle id= %s, userId= %s wasn't found", vehicleId, id)));

        PersonDto clientInfo = null;
        try {
            if (clientInfoIntegrationProperties.isUseBff()) {
                clientInfo = ownrBffClientInfoIntegrationClient.getClientInfo(id);
            } else {
                clientInfo = clientInfoRestTemplateIntegrationClient.getClientInfo(id);
            }
        } catch (IntegrationException e) {
            if (!sellProperties.isIgnorePersonServiceFail()) {
                throw new GarageException(e.getMessage());
            }
        }

        SellCarRequest sellRequest = sellCarRequestService.createRequest(vehicle);

        String notificationMessage;
        try {
            notificationMessage = notificationContentProvider.getNotificationMessage(vehicle, clientInfo, id);
        } catch (InvalidDataException e) {
            try {
                sellCarRequestService.cancelRequest(sellRequest, e.getMessage());
            } catch (EntityNotFoundException ex) {
                throw new GarageException(e.getMessage());
            }
            throw new GarageException(e.getMessage());
        }

        NotificationFromPropertiesMetaData metaData = new NotificationFromPropertiesMetaData();
        metaData.setText(notificationMessage);
        metaData.setSubject(SUBJECT_TEXT);
        metaData.setInfoKey("cell-my-car");
        metaData.setHtml(true);

        try {
            notificationService.sendNotification(metaData);
        } catch (InvalidDataException | IntegrationException e) {
            try {
                sellCarRequestService.cancelRequest(sellRequest, String.format("canceled because of integration error %s", e.getMessage()));
            } catch (EntityNotFoundException ex) {
                throw new GarageException(e.getMessage());
            }
            throw new GarageException(e.getMessage());
        }

        return sellRequest.getId();
    }
}
