package com.anokmik.persistence.util

import android.content.Context
import android.support.annotation.IntDef
import com.anokmik.persistence.R

object TypeEventUtils {

    @JvmStatic
    fun getString(context: Context, @Type type: Long) = when (type) {
        DEFAULT -> context.resources.getString(R.string.event_type_default)
        TICKET -> context.resources.getString(R.string.event_type_ticket)
        HOTEL -> context.resources.getString(R.string.event_type_hotel)
        MEETING -> context.resources.getString(R.string.event_type_meeting)
        PLACE_OF_INTEREST -> context.resources.getString(R.string.event_type_place_of_interest)
        else -> null
    }

    @IntDef(DEFAULT, TICKET, HOTEL, MEETING, PLACE_OF_INTEREST)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Type

    const val DEFAULT = 0L
    const val TICKET = 1L
    const val HOTEL = 2L
    const val MEETING = 3L
    const val PLACE_OF_INTEREST = 4L

}