package com.anokmik.tripassistant.trip.list;

import com.anokmik.persistence.model.Trip;
import com.anokmik.persistence.repository.TripRepository;
import com.anokmik.tripassistant.databinding.adapter.OnItemClickListener;
import com.anokmik.tripassistant.databinding.adapter.RowPresenter;

import java.util.List;

public final class TripsListPresenter implements TripsListContract.Presenter, OnItemClickListener<Trip> {

    private final TripsListContract.View view;
    private final TripRepository tripRepository;
    private final RowPresenter<Trip> tripRowPresenter;

    public TripsListPresenter(TripsListContract.View view) {
        this.view = view;
        this.tripRepository = new TripRepository();
        this.tripRowPresenter = buildRowPresenter(view);
    }

    @Override
    public List<Trip> getTrips() {
        return tripRepository.getAll();
    }

    @Override
    public RowPresenter<Trip> getRowPresenter() {
        return tripRowPresenter;
    }

    @Override
    public void onItemClick(Trip item, int position) {
        view.showTrip(item);
    }

    private RowPresenter<Trip> buildRowPresenter(TripsListContract.View view) {
        return new RowPresenter.Builder<Trip>(view.getRowItemLayoutId(), view.getItemBindingId()).setItemClickListener(this).build();
    }

}