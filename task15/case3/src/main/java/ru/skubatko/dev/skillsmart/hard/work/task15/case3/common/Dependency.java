package ru.skubatko.dev.skillsmart.hard.work.task15.case3.common;

public class Dependency {
}

public class DefaultHtmlTableCreator {
    public HtmlTableRawProvider createTable() {
        return null;
    }
}

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String msg) {
        super(msg);
    }
}

public class ContactPhoneCommunicationDto {
    public String getCompleteNumber() {
        return null;
    }
}

public class HtmlTableRawProvider {
    public Row addRow() {
        return null;
    }

    public String get() {
        return null;
    }
}

public class Row {
    public Header addHeaderColumn(String фио) {
        return null;
    }
}

public class Header {
    public String addColumn(String toString) {
        return null;
    }
}
