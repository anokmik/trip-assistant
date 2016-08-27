package com.anokmik.tripassistant.util;

import android.databinding.ObservableBoolean;
import android.text.TextUtils;
import android.view.View;

import com.anokmik.tripassistant.trip.Mode;

public final class ViewUtils {

    private ViewUtils() {

    }

    public static int showForViewMode(ObservableBoolean state, @Mode int mode) {
        return show(state.get() && isViewMode(mode));
    }

    public static int showForViewMode(boolean state, @Mode int mode) {
        return show(state && isViewMode(mode));
    }

    public static int show(ObservableBoolean state, String value) {
        return show(state.get(), value);
    }

    public static int show(boolean state, String value) {
        return state ? View.VISIBLE : show(value);
    }

    public static int show(String text) {
        return show(!TextUtils.isEmpty(text));
    }

    public static int show(ObservableBoolean observableState) {
        return show(observableState.get());
    }

    public static int show(boolean state) {
        return state ? View.VISIBLE : View.GONE;
    }

    private static boolean isViewMode(@Mode int mode) {
        return mode == Mode.VIEW;
    }

}