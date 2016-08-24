package com.anokmik.tripassistant.databinding;

import android.databinding.BindingConversion;
import android.databinding.ObservableBoolean;

public final class BindingConverters {

    private BindingConverters() {

    }

    @BindingConversion
    public static boolean convertObservableToBoolean(ObservableBoolean observableBoolean) {
        return observableBoolean.get();
    }

}