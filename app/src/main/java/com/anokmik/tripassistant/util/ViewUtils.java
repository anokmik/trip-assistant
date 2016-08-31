package com.anokmik.tripassistant.util;

import android.databinding.ObservableBoolean;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.trip.Mode;

public final class ViewUtils {

    private ViewUtils() {

    }

    public static void showSnackbar(View container, @StringRes int resourceId) {
        if (container != null) {
            Snackbar snackbar = Snackbar.make(container, resourceId, Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(container.getContext(), R.color.colorAccent));
            snackbar.show();
        }
    }

    public static int showForViewMode(ObservableBoolean state, @Mode int mode) {
        return show(stateValue(state) && isViewMode(mode));
    }

    public static int showForViewMode(boolean state, @Mode int mode) {
        return show(state && isViewMode(mode));
    }

    public static int show(ObservableBoolean state, String value) {
        return show(stateValue(state), value);
    }

    public static int show(boolean state, String value) {
        return state ? View.VISIBLE : show(value);
    }

    public static int show(String text) {
        return show(!TextUtils.isEmpty(text));
    }

    public static int show(ObservableBoolean state) {
        return show(stateValue(state));
    }

    public static int show(boolean state) {
        return state ? View.VISIBLE : View.GONE;
    }

    private static boolean isViewMode(@Mode int mode) {
        return mode == Mode.VIEW;
    }

    private static boolean stateValue(ObservableBoolean state) {
        return (state != null) && state.get();
    }

}