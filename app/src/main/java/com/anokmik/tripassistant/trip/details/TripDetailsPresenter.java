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
import com.anokmik.tripassistant.model.ObservableTrip;
import com.anokmik.tripassistant.trip.Mode;
import com.anokmik.tripassistant.util.DateUtils;
import com.anokmik.tripassistant.validator.TextLengthValidator;

import java.util.List;

public final class TripDetailsPresenter implements TripDetailsContract.Presenter, TripDetailsContract.TripEventListener, OnItemClickListener<TripEvent> {

    public final ObservableTrip observableTrip;
    public final ObservableBoolean isEditing;
    public final ObservableBoolean titleValid;

    private final TripRepository tripRepository;
    private final TextLengthValidator validator;
    private final TripDetailsContract.View view;
    private final int mode;
    private final long tripId;
    private final ObservableCompositeList<TripEvent> tripEvents;

    public TripDetailsPresenter(TripDetailsContract.View view, @Mode int mode, long tripId) {
        this.tripRepository = new TripRepository();
        this.validator = new TextLengthValidator();
        this.view = view;
        this.mode = mode;
        this.tripId = tripId;
        this.observableTrip = new ObservableTrip(getTrip());
        this.tripEvents = new ObservableCompositeList<>(new TripEventRepository().getAsyncList(TripEvent_Table.trip.is(tripId)));

        this.isEditing = new ObservableBoolean(isEditingInitialValue());
        this.titleValid = new ObservableBoolean(titleValidInitialValue());
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
        view.showStartDatePickerDialog(observableTrip.getStartDate());
    }

    @Override
    public void showFinishDatePicker() {
        view.showFinishDatePickerDialog(observableTrip.getFinishDate());
    }

    @Override
    public void setStartDate(long startDate) {
        observableTrip.setStartDate(startDate);
    }

    @Override
    public void setFinishDate(long finishDate) {
        observableTrip.setFinishDate(finishDate);
    }

    @Override
    public boolean validFields() {
        titleValid.set(validator.notEmpty(observableTrip.getTitle()));
        boolean validDates = DateUtils.validDates(observableTrip.getStartDate(), observableTrip.getFinishDate());
        if (!validDates) {
            view.showDatesInvalidError();
        }
        return titleValid.get() && validDates;
    }

    @Override
    public void save() {
        if (validFields()) {
            isEditing.set(false);
            observableTrip.save();
            handleSave();
        }
    }

    @Override
    public void cancel() {
        handleCancel();
    }

    @Override
    public void edit() {
        isEditing.set(true);
        view.enableSaveControls();
    }

    @Override
    public void delete() {
        for (TripEvent tripEvent : getTripEvents()) {
            tripEvent.delete();
        }
        observableTrip.delete();
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

    private boolean isEditingInitialValue() {
        switch (mode) {
            case Mode.ADD:
                return true;
            case Mode.VIEW:
                return false;
            default:
                throw new IllegalArgumentException();
        }
    }

    private boolean titleValidInitialValue() {
        switch (mode) {
            case Mode.ADD:
            case Mode.VIEW:
                return true;
            default:
                throw new IllegalArgumentException();
        }
    }

    private Trip getTrip() {
        switch (mode) {
            case Mode.ADD:
                Trip trip = new Trip();
                trip.startDate = System.currentTimeMillis();
                trip.finishDate = System.currentTimeMillis();
                return trip;
            case Mode.VIEW:
                return tripRepository.get(Trip_Table.id.is(tripId));
            default:
                throw new IllegalArgumentException();
        }
    }

    private void handleSave() {
        switch (mode) {
            case Mode.ADD:
                view.back();
                break;
            case Mode.VIEW:
                view.enableEditControls();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void handleCancel() {
        switch (mode) {
            case Mode.ADD:
                view.back();
                break;
            case Mode.VIEW:
                isEditing.set(false);
                view.enableEditControls();
                observableTrip.set(getTrip());
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

}