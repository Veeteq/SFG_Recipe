package com.wojnarowicz.sfg.recipe.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static LocalDate parse(String dateString) {
        return DATE_FORMATTER.parse(dateString, LocalDate::from);
    }

    public static String format(LocalDate localDate) {
        if (localDate == null)
            return null;
        return DATE_FORMATTER.format(localDate);
    }
}
