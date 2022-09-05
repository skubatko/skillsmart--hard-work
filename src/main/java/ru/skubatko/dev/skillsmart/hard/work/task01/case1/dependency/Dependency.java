package ru.skubatko.dev.skillsmart.hard.work.task01.case1.dependency;

import java.util.Optional;

public class Dependency {
}


public class SellCarRequestService {
    public SellCarRequest createRequest(Vehicle vehicle) {
        return null;
    }

    public void cancelRequest(SellCarRequest sellRequest, String message) {

    }
}

public class VehicleRepository {
    public Optional<Vehicle> findById(int vehicleId) {
        return null;
    }
}

public class NotificationService {
    public void sendNotification(NotificationFromPropertiesMetaData metaData) {

    }
}

public class PersonDto {}

public class ClientInfoIntegrationClient<T> {
    public T getClientInfo(String id) {
        return null;
    }
}

public class SellProperties {
    public boolean isIgnorePersonServiceFail() {
        return false;
    }
}

public class ClientInfoIntegrationProperties {
    public boolean isUseBff() {
        return false;
    }
}

public class ClientNotificationContentProvider {
    public String getNotificationMessage(Vehicle vehicle, PersonDto clientInfo, String id) {
        return null;
    }
}

public class Vehicle {}

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String format) {}
}

public class IntegrationException extends RuntimeException {}

public class GarageException extends RuntimeException {
    public GarageException(String message) {}
}

public class SellCarRequest {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

public class InvalidDataException extends RuntimeException {}

public class EntityNotFoundException extends RuntimeException {}

public class NotificationFromPropertiesMetaData {
    private String text;
    private String subject;
    private String infoKey;
    private boolean html;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setInfoKey(String infoKey) {
        this.infoKey = infoKey;
    }

    public String getInfoKey() {
        return infoKey;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }

    public boolean isHtml() {
        return html;
    }
}
