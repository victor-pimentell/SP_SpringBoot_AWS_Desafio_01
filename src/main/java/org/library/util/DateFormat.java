package org.library.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {

    public static LocalDate getDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, dateTimeFormatter);
    }

    public static String dateFormat(LocalDate localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(dateTimeFormatter);
    }
}
