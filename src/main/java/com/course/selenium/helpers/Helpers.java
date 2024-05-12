package com.course.selenium.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Helpers {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static String getRandomEmail() {
        return String.format("john_%d@gmail.com", System.currentTimeMillis());
    }

    public static String today() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.format(DATE_FORMATTER);
    }

    public static String nextWeek() {
        LocalDate nextWeek = LocalDate.now().plusDays(7);
        return nextWeek.format(DATE_FORMATTER);
    }
}
