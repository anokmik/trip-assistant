package com.anokmik.tripassistant.trip.event;

import android.support.annotation.LayoutRes;

import com.anokmik.persistence.model.PhotoAttachment;
import com.anokmik.persistence.model.TripEvent;
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter;

import java.util.List;

public interface TripEventContract {

    interface Presenter {

        List<PhotoAttachment> getPhotoAttachments();

        ViewHolderPresenter<PhotoAttachment> getViewHolderPresenter();

        void showStartDatePicker();

        void showFinishDatePicker();

        void setStartDate(long startDate);

        void setFinishDate(long finishDate);

        boolean validFields();

        void save();

        void cancel();

        void edit();

        void delete();

        void addPhotoAttachment(String path);

    }

    interface PhotoAttachmentListener {

        void takePhoto();

        void pickPhoto();

        void deletePhoto(int position);

    }

    interface View {

        @LayoutRes
        int getRowItemLayoutId();

        int getItemBindingId();

        int getAdapterPositionProviderBindingId();

        int getItemListenerBindingId();

        int getItemIsEditingBindingId();

        void showStartDatePickerDialog(long startDate);

        void showFinishDatePickerDialog(long finishDate);

        void showDatesInvalidError();

        void takePhotoAttachment();

        void pickPhotoAttachment();

        void enableSaveControls();

        void enableEditControls();

        void back();

    }

}