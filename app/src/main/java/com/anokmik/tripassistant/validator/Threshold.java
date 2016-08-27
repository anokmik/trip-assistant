package com.anokmik.tripassistant.validator;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({Threshold.NAME_LENGTH})
@Retention(RetentionPolicy.SOURCE)
public @interface Threshold {
    int NAME_LENGTH = 5;
}