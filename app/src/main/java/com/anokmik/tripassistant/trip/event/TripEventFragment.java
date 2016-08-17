package com.anokmik.tripassistant.trip.event;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.anokmik.tripassistant.trip.details.TripDetailsFragment;

public final class TripEventFragment extends Fragment {

    public static final String KEY_TRIP_EVENT_ID = "key_trip_event_id";

    public static TripEventFragment add(long tripId) {
        Bundle arguments = new Bundle();
        arguments.putLong(TripDetailsFragment.KEY_TRIP_ID, tripId);
        TripEventFragment tripEventFragment = new TripEventFragment();
        tripEventFragment.setArguments(arguments);
        return tripEventFragment;
    }

    public static TripEventFragment view(long tripEventId) {
        Bundle arguments = new Bundle();
        arguments.putLong(KEY_TRIP_EVENT_ID, tripEventId);
        TripEventFragment tripDetailsFragment = new TripEventFragment();
        tripDetailsFragment.setArguments(arguments);
        return tripDetailsFragment;
    }

    private long getTripId() {
        return getArguments().getLong(TripDetailsFragment.KEY_TRIP_ID);
    }

    private long getTripEventId() {
        return getArguments().getLong(KEY_TRIP_EVENT_ID);
    }

}