package com.anokmik.tripassistant.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val DATE_FORMAT = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    private val FORMAT = "%s - %s"

    @JvmStatic
    fun validDates(startDate: Long?, finishDate: Long?): Boolean {
        if (startDate == null || finishDate == null) {
            return false
        }

        val calendar = Calendar.getInstance()

        calendar.timeInMillis = startDate
        val startYear = calendar.get(Calendar.YEAR)
        val startMonth = calendar.get(Calendar.MONTH)
        val startDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        calendar.timeInMillis = finishDate
        val finishYear = calendar.get(Calendar.YEAR)
        val finishMonth = calendar.get(Calendar.MONTH)
        val finishDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        return finishYear >= startYear && finishMonth >= startMonth && finishDayOfMonth >= startDayOfMonth
    }

    @JvmStatic
    fun format(startDate: Long, finishDate: Long): String {
        val startDateValue = toString(startDate)
        val finishDateValue = toString(finishDate)
        return if (startDateValue.equals(finishDateValue)) startDateValue else String.format(FORMAT, startDateValue, finishDateValue)
    }

    @JvmStatic
    fun toString(time: Long) = DATE_FORMAT.format(Date(time))

    @JvmStatic
    fun toTime(date: String): Long {
        try {
            return DATE_FORMAT.parse(date).time
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

}