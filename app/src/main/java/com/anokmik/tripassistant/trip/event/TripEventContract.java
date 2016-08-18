package com.anokmik.tripassistant.trip.event;

import android.support.annotation.LayoutRes;

import com.anokmik.persistence.model.PhotoAttachment;
import com.anokmik.persistence.model.TripEvent;
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter;

import java.util.List;

public interface TripEventContract {

    interface Presenter {

        TripEvent getTripEvent();

        List<PhotoAttachment> getPhotoAttachments();

        ViewHolderPresenter<PhotoAttachment> getViewHolderPresenter();

        void showStartDatePicker();

        void showFinishDatePicker();

        void setStartDate(long startDate);

        void setFinishDate(long finishDate);

        void save();

        void edit();

        void delete();

        void addPhotoAttachment(String path);

    }

    interface PhotoAttachmentListener {

        void takePhoto();

        void pickPhoto();

        void deletePhoto(PhotoAttachment photoAttachment);

    }

    interface View {

        @LayoutRes
        int getRowItemLayoutId();

        int getItemBindingId();

        int getItemListenerBindingId();

        int getItemIsEditingBindingId();

        void showStartDatePickerDialog(long startDate);

        void showFinishDatePickerDialog(long finishDate);

        void takePhotoAttachment();

        void pickPhotoAttachment();

        void enableSaveMode();

        void enableEditMode();

        void back();

    }

}