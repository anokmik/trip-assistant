package com.anokmik.tripassistant.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private static final String FORMAT = "%s - %s";

    private DateUtils() {

    }

    public static boolean validDates(long startDate, long finishDate) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(startDate);
        int startYear = calendar.get(Calendar.YEAR);
        int startMonth = calendar.get(Calendar.MONTH);
        int startDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTimeInMillis(finishDate);
        int finishYear = calendar.get(Calendar.YEAR);
        int finishMonth = calendar.get(Calendar.MONTH);
        int finishDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        return (finishYear >= startYear) && (finishMonth >= startMonth) && (finishDayOfMonth >= startDayOfMonth);
    }

    public static String format(long startDate, long finishDate) {
        String startDateValue = toString(startDate);
        String finishDateValue = toString(finishDate);
        return startDateValue.equals(finishDateValue)
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