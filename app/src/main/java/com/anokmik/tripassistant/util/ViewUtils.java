package com.anokmik.tripassistant.util;

import android.databinding.ObservableBoolean;
import android.text.TextUtils;
import android.view.View;

public final class ViewUtils {

    private ViewUtils() {

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

}