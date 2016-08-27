package com.anokmik.tripassistant.validator;

import android.text.TextUtils;

public final class TextLengthValidator {

    public boolean higherThan(String text, @Threshold int threshold) {
        return notEmpty(text) && (text.length() > threshold);
    }

    public boolean lowerThan(String text, @Threshold int threshold) {
        return notEmpty(text) && (text.length() < threshold);
    }

    public boolean notEmpty(String text) {
        return !TextUtils.isEmpty(text);
    }

}