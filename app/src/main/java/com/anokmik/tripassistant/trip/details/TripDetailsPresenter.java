package com.anokmik.tripassistant.trip.details;

import android.databinding.ObservableBoolean;

import com.anokmik.persistence.model.Trip;
import com.anokmik.persistence.model.TripEvent;
import com.anokmik.persistence.model.TripEvent_Table;
import com.anokmik.persistence.model.Trip_Table;
import com.anokmik.persistence.repository.TripEventRepository;
import com.anokmik.persistence.repository.TripRepository;
import com.anokmik.tripassistant.databinding.ObservableCompositeList;
import com.anokmik.tripassistant.databinding.adapter.OnItemClickListener;
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter;

import java.util.List;

public final class TripDetailsPresenter implements TripDetailsContract.Presenter, TripDetailsContract.TripEventListener, OnItemClickListener<TripEvent> {

    public final ObservableBoolean isEditing;
    public final ObservableBoolean titleValid;

    private final TripDetailsContract.View view;
    private final Trip trip;
    private final ObservableCompositeList<TripEvent> tripEvents;

    public TripDetailsPresenter(TripDetailsContract.View view, long tripId) {
        this.isEditing = new ObservableBoolean(tripId == 0);
        this.titleValid = new ObservableBoolean(tripId != 0);
        this.view = view;
        this.trip = new TripRepository().get(Trip_Table.id.is(tripId));
        this.tripEvents = new ObservableCompositeList<>(new TripEventRepository().getAsyncList(TripEvent_Table.trip.is(tripId)));
    }

    @Override
    public Trip getTrip() {
        return trip;
    }

    @Override
    public List<TripEvent> getTripEvents() {
        return tripEvents;
    }

    @Override
    public ViewHolderPresenter<TripEvent> getViewHolderPresenter() {
        return new ViewHolderPresenter.Builder<TripEvent>(view.getRowItemLayoutId(), view.getItemBindingId())
                .setAdapterPositionProviderBindingId(view.getAdapterPositionProviderBindingId())
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
        view.enableEditMode();
    }

    @Override
    public void edit() {
        isEditing.set(true);
        view.enableSaveMode();
    }

    @Override
    public void delete() {
        for (TripEvent tripEvent : getTripEvents()) {
            tripEvent.delete();
        }
        trip.delete();
        view.back();
    }

    @Override
    public void addEvent() {
        view.addTripEvent();
    }

    @Override
    public void deleteEvent(int position) {
        tripEvents.remove(position);
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