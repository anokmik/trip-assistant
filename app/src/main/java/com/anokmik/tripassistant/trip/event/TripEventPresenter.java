package com.anokmik.tripassistant.trip.event;

import android.databinding.ObservableBoolean;

import com.anokmik.persistence.model.PhotoAttachment;
import com.anokmik.persistence.model.PhotoAttachment_Table;
import com.anokmik.persistence.model.TripEvent;
import com.anokmik.persistence.model.TripEvent_Table;
import com.anokmik.persistence.model.Trip_Table;
import com.anokmik.persistence.repository.PhotoAttachmentRepository;
import com.anokmik.persistence.repository.TripEventRepository;
import com.anokmik.persistence.repository.TripRepository;
import com.anokmik.tripassistant.databinding.ObservableCompositeList;
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter;
import com.anokmik.tripassistant.model.ObservableTripEvent;
import com.anokmik.tripassistant.trip.Mode;
import com.anokmik.tripassistant.util.DateUtils;
import com.anokmik.tripassistant.validator.TextLengthValidator;

import java.util.List;

public final class TripEventPresenter implements TripEventContract.Presenter, TripEventContract.PhotoAttachmentListener {

    public final ObservableTripEvent observableTripEvent;
    public final ObservableBoolean isEditing;
    public final ObservableBoolean nameValid;

    private final TripRepository tripRepository;
    private final TripEventRepository tripEventRepository;
    private final TextLengthValidator validator;
    private final TripEventContract.View view;
    private final int mode;
    private final long tripId;
    private final long tripEventId;
    private final ObservableCompositeList<PhotoAttachment> photoAttachments;

    public TripEventPresenter(TripEventContract.View view, @Mode int mode, long tripId, long tripEventId) {
        this.tripRepository = new TripRepository();
        this.tripEventRepository = new TripEventRepository();
        this.validator = new TextLengthValidator();
        this.view = view;
        this.mode = mode;
        this.tripId = tripId;
        this.tripEventId = tripEventId;
        this.observableTripEvent = new ObservableTripEvent(getTripEvent());
        this.photoAttachments = new ObservableCompositeList<>(new PhotoAttachmentRepository().getAsyncList(PhotoAttachment_Table.tripEvent.is(tripEventId)));

        this.isEditing = new ObservableBoolean(isEditingInitialValue());
        this.nameValid = new ObservableBoolean(nameValidInitialValue());
    }

    @Override
    public List<PhotoAttachment> getPhotoAttachments() {
        return photoAttachments;
    }

    @Override
    public ViewHolderPresenter<PhotoAttachment> getViewHolderPresenter() {
        return new ViewHolderPresenter.Builder<PhotoAttachment>(view.getRowItemLayoutId(), view.getItemBindingId())
                .setAdapterPositionProviderBindingId(view.getAdapterPositionProviderBindingId())
                .mapVariable(view.getItemListenerBindingId(), this)
                .mapVariable(view.getItemIsEditingBindingId(), isEditing)
                .build();
    }

    @Override
    public void showStartDatePicker() {
        view.showStartDatePickerDialog(observableTripEvent.getStartDate());
    }

    @Override
    public void showFinishDatePicker() {
        view.showFinishDatePickerDialog(observableTripEvent.getFinishDate());
    }

    @Override
    public void setStartDate(long startDate) {
        observableTripEvent.setStartDate(startDate);
    }

    @Override
    public void setFinishDate(long finishDate) {
        observableTripEvent.setFinishDate(finishDate);
    }

    @Override
    public boolean validFields() {
        nameValid.set(validator.notEmpty(observableTripEvent.getName()));
        boolean validDates = DateUtils.validDates(observableTripEvent.getStartDate(), observableTripEvent.getFinishDate());
        if (!validDates) {
            view.showDatesInvalidError();
        }
        return nameValid.get() && validDates;
    }

    @Override
    public void save() {
        if (validFields()) {
            isEditing.set(false);
            observableTripEvent.save();
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
        for (PhotoAttachment photoAttachment : getPhotoAttachments()) {
            photoAttachment.delete();
        }
        observableTripEvent.delete();
        view.back();
    }

    @Override
    public void addPhotoAttachment(String path) {
        PhotoAttachment photoAttachment = new PhotoAttachment();
        photoAttachment.tripEvent = observableTripEvent.get();
        photoAttachment.path = path;
        photoAttachment.save();
    }

    @Override
    public void takePhoto() {
        view.takePhotoAttachment();
    }

    @Override
    public void pickPhoto() {
        view.pickPhotoAttachment();
    }

    @Override
    public void deletePhoto(int position) {
        photoAttachments.remove(position);
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

    private boolean nameValidInitialValue() {
        switch (mode) {
            case Mode.ADD:
            case Mode.VIEW:
                return true;
            default:
                throw new IllegalArgumentException();
        }
    }

    private TripEvent getTripEvent() {
        switch (mode) {
            case Mode.ADD:
                TripEvent tripEvent = new TripEvent();
                tripEvent.startDate = System.currentTimeMillis();
                tripEvent.finishDate = System.currentTimeMillis();
                tripEvent.trip = tripRepository.get(Trip_Table.id.is(tripId));
                return tripEvent;
            case Mode.VIEW:
                return tripEventRepository.get(TripEvent_Table.id.is(tripEventId));
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
                observableTripEvent.set(getTripEvent());
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

}