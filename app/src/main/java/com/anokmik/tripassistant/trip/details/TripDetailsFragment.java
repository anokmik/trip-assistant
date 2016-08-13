package com.anokmik.tripassistant.trip.details;

import android.support.v4.app.Fragment;

public final class TripDetailsFragment extends Fragment {

    public static TripDetailsFragment add() {
        return new TripDetailsFragment();
    }

    public static TripDetailsFragment edit(long tripId) {
        return new TripDetailsFragment();
    }

    public static TripDetailsFragment view(long tripId) {
        return new TripDetailsFragment();
    }

}