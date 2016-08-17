package com.anokmik.tripassistant.trip.details;

import android.support.annotation.LayoutRes;

import com.anokmik.persistence.model.Trip;
import com.anokmik.persistence.model.TripEvent;
import com.anokmik.tripassistant.databinding.adapter.RowPresenter;

import java.util.List;

public interface TripDetailsContract {

    interface Presenter {

        Trip getTrip();

        List<TripEvent> getTripEvents();

        RowPresenter<TripEvent> getRowPresenter();

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

        void deleteEvent(long tripEventId);

    }

    interface View {

        @LayoutRes
        int getRowItemLayoutId();

        int getItemBindingId();

        int getItemListenerBindingId();

        int getItemIsEditingBindingId();

        void showStartDatePickerDialog(long startDate);

        void showFinishDatePickerDialog(long finishDate);

        void addTripEvent();

        void viewTripEvent(long tripEventId);

    }

}