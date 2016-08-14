package com.anokmik.tripassistant.util;

import android.text.TextUtils;
import android.view.View;

public final class ViewUtils {

    private ViewUtils() {

    }

    public static int hideIfEmpty(String value) {
        return TextUtils.isEmpty(value) ? View.GONE : View.VISIBLE;
    }

}