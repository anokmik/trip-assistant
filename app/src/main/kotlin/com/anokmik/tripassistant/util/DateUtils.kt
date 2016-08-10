package com.anokmik.tripassistant.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val DATE_FORMAT = SimpleDateFormat("dd/MM/yyyy", Locale.US)

    private val FORMAT = "%s - %s"

    @JvmStatic
    fun format(startDate: Long, finishDate: Long): String {
        val startDateValue = toString(startDate)
        val finishDateValue = toString(finishDate)
        return if (startDateValue.equals(finishDateValue)) startDateValue else String.format(FORMAT, startDateValue, finishDateValue)
    }

    @JvmStatic
    fun toString(time: Long): String {
        return DATE_FORMAT.format(Date(time))
    }

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