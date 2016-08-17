package com.anokmik.tripassistant.trip.details;

import android.databinding.ObservableBoolean;

import com.anokmik.persistence.model.Trip;
import com.anokmik.persistence.model.TripEvent;
import com.anokmik.persistence.model.TripEvent_Table;
import com.anokmik.persistence.model.Trip_Table;
import com.anokmik.persistence.repository.TripEventRepository;
import com.anokmik.persistence.repository.TripRepository;
import com.anokmik.tripassistant.databinding.adapter.OnItemClickListener;
import com.anokmik.tripassistant.databinding.adapter.RowPresenter;

import java.util.List;

public final class TripDetailsPresenter implements TripDetailsContract.Presenter, TripDetailsContract.TripEventListener, OnItemClickListener<TripEvent> {

    public final ObservableBoolean isEditing;
    public final ObservableBoolean titleValid;

    private final TripDetailsContract.View view;
    private final TripRepository tripRepository;
    private final TripEventRepository tripEventRepository;
    private final Trip trip;

    public TripDetailsPresenter(TripDetailsContract.View view, long tripId) {
        this.isEditing = new ObservableBoolean(tripId == 0);
        this.titleValid = new ObservableBoolean(true);
        this.view = view;
        this.tripRepository = new TripRepository();
        this.tripEventRepository = new TripEventRepository();
        this.trip = tripRepository.get(Trip_Table.id.is(tripId));
    }

    @Override
    public Trip getTrip() {
        return trip;
    }

    @Override
    public List<TripEvent> getTripEvents() {
        return tripEventRepository.getList(TripEvent_Table.trip.is(trip.id));
    }

    @Override
    public RowPresenter<TripEvent> getRowPresenter() {
        return new RowPresenter.Builder<TripEvent>(view.getRowItemLayoutId(), view.getItemBindingId())
                .setItemClickListener(this)
                .mapVariable(view.getItemListenerBindingId(), this)
                .mapVariable(view.getItemIsEditingBindingId(), isEditing)
                .build();
    }

    @Override
    public void showStartDatePicker() {
        view.showStartDatePickerDialog(trip.startDate);
    }

    @Override
    public void showFinishDatePicker() {
        view.showFinishDatePickerDialog(trip.finishDate);
    }

    @Override
    public void setStartDate(long startDate) {
        trip.startDate = startDate;
    }

    @Override
    public void setFinishDate(long finishDate) {
        trip.finishDate = finishDate;
    }

    @Override
    public void save() {
        isEditing.set(false);
        trip.save();
    }

    @Override
    public void edit() {
        isEditing.set(true);
    }

    @Override
    public void delete() {
        tripRepository.delete(Trip_Table.id.is(trip.id));
    }

    @Override
    public void addEvent() {
        view.addTripEvent();
    }

    @Override
    public void deleteEvent(long tripEventId) {
        tripEventRepository.delete(TripEvent_Table.id.is(tripEventId));
    }

    @Override
    public void onItemClick(TripEvent item, int position) {
        view.viewTripEvent(item.id);
    }

    public String getTripTitle() {
        return trip.title;
    }

    public void setTripTitle(String title) {
        trip.title = title;
    }

    public String getTripDescription() {
        return trip.description;
    }

    public void setTripDescription(String description) {
        trip.description = description;
    }

    public long getTripStartDate() {
        return trip.startDate;
    }

    public long getTripFinishDate() {
        return trip.finishDate;
    }

}