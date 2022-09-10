package ru.skubatko.dev.skillsmart;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RunWith(JQF.class)
public class DateFormatterTest {

    @Fuzz
    public void fuzzLocalDateTime(String date, String pattern) throws IllegalArgumentException, DateTimeParseException {
        LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
    }
}
