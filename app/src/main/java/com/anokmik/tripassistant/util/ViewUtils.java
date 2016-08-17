package com.anokmik.tripassistant.util;

import android.databinding.ObservableBoolean;
import android.text.TextUtils;
import android.view.View;

public final class ViewUtils {

    private ViewUtils() {

    }

    public static int show(ObservableBoolean value) {
        return show(value.get());
    }

    public static int show(boolean value) {
        return value ? View.VISIBLE : View.GONE;
    }

    public static int hideIfEmpty(String value) {
        return TextUtils.isEmpty(value) ? View.GONE : View.VISIBLE;
    }

}