package com.anokmik.tripassistant.trip.details

import android.support.v4.app.Fragment

class TripDetailsFragment : Fragment() {

    companion object {

        fun add() = TripDetailsFragment()

        fun edit(tripId: Long?) = TripDetailsFragment()

        fun view(tripId: Long?) = TripDetailsFragment()

    }

}