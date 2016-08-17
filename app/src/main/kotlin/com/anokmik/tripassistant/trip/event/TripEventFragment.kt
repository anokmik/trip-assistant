package com.anokmik.tripassistant.trip.event

import android.os.Bundle
import android.support.v4.app.Fragment
import com.anokmik.tripassistant.trip.details.TripDetailsFragment

class TripEventFragment : Fragment() {

    private val tripId by lazy { arguments.getLong(TripDetailsFragment.KEY_TRIP_ID) }

    private val tripEventId by lazy { arguments.getLong(KEY_TRIP_EVENT_ID) }

    companion object {

        val KEY_TRIP_EVENT_ID = "key_trip_event_id"

        fun add(tripId: Long): TripEventFragment {
            val arguments = Bundle()
            arguments.putLong(TripDetailsFragment.KEY_TRIP_ID, tripId)
            val tripEventFragment = TripEventFragment()
            tripEventFragment.arguments = arguments
            return tripEventFragment
        }

        fun view(tripEventId: Long): TripEventFragment {
            val arguments = Bundle()
            arguments.putLong(KEY_TRIP_EVENT_ID, tripEventId)
            val tripDetailsFragment = TripEventFragment()
            tripDetailsFragment.arguments = arguments
            return tripDetailsFragment
        }

    }

}