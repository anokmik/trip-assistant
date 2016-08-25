package com.anokmik.tripassistant.trip.details;

import android.support.annotation.LayoutRes;

import com.anokmik.persistence.model.Trip;
import com.anokmik.persistence.model.TripEvent;
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter;

import java.util.List;

public interface TripDetailsContract {

    interface Presenter {

        Trip getTrip();

        List<TripEvent> getTripEvents();

        ViewHolderPresenter<TripEvent> getViewHolderPresenter();

        void showStartDatePicker();

        void showFinishDatePicker();

        void setStartDate(long startDate);

        void setFinishDate(long finishDate);

        void save();

        void edit();

        void delete();

    }

    interface TripEventListener {

        void addEvent();

        void deleteEvent(int position);

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

        void addTripEvent();

        void viewTripEvent(long tripEventId);

        void enableSaveMode();

        void enableEditMode();

        void back();

    }

}