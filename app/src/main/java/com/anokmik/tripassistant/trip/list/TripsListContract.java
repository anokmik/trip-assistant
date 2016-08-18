package com.anokmik.tripassistant.trip.list;

import android.support.annotation.LayoutRes;

import com.anokmik.persistence.model.Trip;
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter;

import java.util.List;

public interface TripsListContract {

    interface View {

        @LayoutRes
        int getRowItemLayoutId();

        int getItemBindingId();

        void showTrip(Trip trip);

    }

    interface Presenter {

        List<Trip> getTrips();

        ViewHolderPresenter<Trip> getViewHolderPresenter();

    }

}