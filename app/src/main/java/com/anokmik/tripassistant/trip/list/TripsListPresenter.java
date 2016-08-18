package com.anokmik.tripassistant.trip.list;

import com.anokmik.persistence.model.Trip;
import com.anokmik.persistence.repository.TripRepository;
import com.anokmik.tripassistant.databinding.adapter.OnItemClickListener;
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter;

import java.util.List;

public final class TripsListPresenter implements TripsListContract.Presenter, OnItemClickListener<Trip> {

    private final TripsListContract.View view;
    private final TripRepository tripRepository;
    private final ViewHolderPresenter<Trip> tripViewHolderPresenter;

    public TripsListPresenter(TripsListContract.View view) {
        this.view = view;
        this.tripRepository = new TripRepository();
        this.tripViewHolderPresenter = buildRowPresenter(view);
    }

    @Override
    public List<Trip> getTrips() {
        return tripRepository.getAll();
    }

    @Override
    public ViewHolderPresenter<Trip> getViewHolderPresenter() {
        return tripViewHolderPresenter;
    }

    @Override
    public void onItemClick(Trip item, int position) {
        view.showTrip(item);
    }

    private ViewHolderPresenter<Trip> buildRowPresenter(TripsListContract.View view) {
        return new ViewHolderPresenter.Builder<Trip>(view.getRowItemLayoutId(), view.getItemBindingId()).setItemClickListener(this).build();
    }

}