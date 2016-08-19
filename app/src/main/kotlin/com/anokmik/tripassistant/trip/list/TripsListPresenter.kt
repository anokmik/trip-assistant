package com.anokmik.tripassistant.trip.list

import com.anokmik.persistence.model.Trip
import com.anokmik.persistence.repository.TripRepository
import com.anokmik.tripassistant.databinding.adapter.OnItemClickListener
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter

class TripsListPresenter(private val view: TripsListContract.View) : TripsListContract.Presenter, OnItemClickListener<Trip> {

    private val tripRepository = TripRepository()

    override val trips = tripRepository.getAll()

    override val viewHolderPresenter = ViewHolderPresenter.Builder<Trip>(view.rowItemLayoutId, view.itemBindingId).setItemClickListener(this).build()

    override fun onItemClick(item: Trip?, position: Int) = view.showTrip(item)

}