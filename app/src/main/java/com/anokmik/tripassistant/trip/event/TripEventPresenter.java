package com.anokmik.tripassistant.trip.event;

import android.databinding.ObservableBoolean;

import com.anokmik.persistence.model.PhotoAttachment;
import com.anokmik.persistence.model.PhotoAttachment_Table;
import com.anokmik.persistence.model.TripEvent;
import com.anokmik.persistence.model.TripEvent_Table;
import com.anokmik.persistence.repository.PhotoAttachmentRepository;
import com.anokmik.persistence.repository.TripEventRepository;
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter;

import java.util.List;

public final class TripEventPresenter implements TripEventContract.Presenter,
        TripEventContract.PhotoAttachmentListener {

    public final ObservableBoolean isEditing;
    public final ObservableBoolean nameValid;

    private final TripEventContract.View view;
    private final TripEvent tripEvent;
    private final List<PhotoAttachment> photoAttachments;

    public TripEventPresenter(TripEventContract.View view, long tripEventId) {
        this.isEditing = new ObservableBoolean(tripEventId == 0);
        this.nameValid = new ObservableBoolean(tripEventId != 0);
        this.view = view;
        this.tripEvent = new TripEventRepository().get(TripEvent_Table.id.is(tripEventId));
        this.photoAttachments = new PhotoAttachmentRepository().getList(PhotoAttachment_Table.tripEvent.is(tripEventId));
    }

    @Override
    public TripEvent getTripEvent() {
        return tripEvent;
    }

    @Override
    public List<PhotoAttachment> getPhotoAttachments() {
        return photoAttachments;
    }

    @Override
    public ViewHolderPresenter<PhotoAttachment> getViewHolderPresenter() {
        return new ViewHolderPresenter.Builder<PhotoAttachment>
                (view.getRowItemLayoutId(), view.getItemBindingId())
                .mapVariable(view.getItemListenerBindingId(), this)
                .mapVariable(view.getItemIsEditingBindingId(), isEditing)
                .build();
    }

    @Override
    public void showStartDatePicker() {
        view.showStartDatePickerDialog(tripEvent.startDate);
    }

    @Override
    public void showFinishDatePicker() {
        view.showFinishDatePickerDialog(tripEvent.finishDate);
    }

    @Override
    public void setStartDate(long startDate) {
        tripEvent.startDate = startDate;
    }

    @Override
    public void setFinishDate(long finishDate) {
        tripEvent.finishDate = finishDate;
    }

    @Override
    public void save() {
        isEditing.set(false);
        tripEvent.save();
        view.enableEditMode();
    }

    @Override
    public void edit() {
        isEditing.set(true);
        view.enableSaveMode();
    }

    @Override
    public void delete() {
        for (PhotoAttachment photoAttachment : getPhotoAttachments()) {
            photoAttachment.delete();
        }
        tripEvent.delete();
    }

    @Override
    public void addPhotoAttachment(String path) {
        PhotoAttachment photoAttachment = new PhotoAttachment();
        photoAttachment.tripEvent = tripEvent;
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
    public void deletePhoto(PhotoAttachment photoAttachment) {
        photoAttachment.delete();
    }

    public String getTripEventName() {
        return tripEvent.name;
    }

    public void setTripEventName(String name) {
        tripEvent.name = name;
    }

    public String getTripEventComment() {
        return tripEvent.comment;
    }

    public void setTripEventComment(String comment) {
        tripEvent.comment = comment;
    }

    public long getTripEventStartDate() {
        return tripEvent.startDate;
    }

    public long getTripEventFinishDate() {
        return tripEvent.finishDate;
    }

}