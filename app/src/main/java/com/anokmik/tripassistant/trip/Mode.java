package com.anokmik.tripassistant.trip;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({Mode.ADD, Mode.VIEW})
@Retention(RetentionPolicy.SOURCE)
public @interface Mode {
    int ADD = 1212;
    int VIEW = 1221;
}