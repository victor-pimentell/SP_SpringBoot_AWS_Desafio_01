package org.library.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class DateFormat {

    public static LocalDate getDate(Scanner sc) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = null;

        while (localDate == null) {
            try {
                String date = sc.nextLine();
                dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                localDate = LocalDate.parse(date, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.out.print("Date format invalid, please follow the example (dd/mm/yyyy): ");
            }
        }
        return localDate;
    }

    public static String dateFormat(LocalDate localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(dateTimeFormatter);
    }
}
