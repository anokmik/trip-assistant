package com.anokmik.tripassistant.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private static final String FORMAT = "%s - %s";

    private DateUtils() {

    }

    public static String format(long startDate, long finishDate) {
        String startDateValue = toString(startDate);
        String finishDateValue = toString(finishDate);
        return (startDateValue.equals(finishDateValue))
                ? startDateValue : String.format(FORMAT, startDateValue, finishDateValue);
    }

    public static String toString(long time) {
        return DATE_FORMAT.format(new Date(time));
    }

    public static long toTime(String date) {
        try {
            return DATE_FORMAT.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}