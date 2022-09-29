package ru.skubatko.dev.skillsmart.hard.work.task05.case2;

public class Dependency {
}

public class BusinessException extends RuntimeException {
    public BusinessException(String code, String s) {}
}

public class Errors {
    public static final String VEHICLE_MODEL_NOT_FOUND_BY_ID = "";
    public static final String VEHICLE_BRAND_ID_IS_NULL = "";
    public static final String VEHICLE_MODEL_KIND_ID_IS_NULL = "";
    public static final String VEHICLE_BRAND_NOT_FOUND_BY_ID = "";
    public static final String VEHICLE_KIND_NOT_FOUND_BY_ID = "";
    public static final String INVALID_VEHICLE_KIND_REFERENCE = "";
    public static final String PROPERTY_FORM_NOT_FOUND_BY_ID = "";
    public static final String INVALID_PROPERTY_FORM_REFERENCE = "";
}

public class MessageService {
    public String getLocalizedMessage(String s) {
        return null;
    }
}
