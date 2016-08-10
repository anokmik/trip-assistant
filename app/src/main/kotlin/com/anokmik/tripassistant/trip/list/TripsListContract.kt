package com.anokmik.tripassistant.trip.list

import com.anokmik.persistence.model.Trip
import com.anokmik.tripassistant.databinding.adapter.RowPresenter

interface TripsListContract {

    interface View {

        val rowItemLayoutId: Int

        val itemBindingId: Int

        fun showTrip(trip: Trip?)

    }

    interface Presenter {

        val trips: List<Trip?>

        val rowPresenter: RowPresenter<Trip>

    }

}