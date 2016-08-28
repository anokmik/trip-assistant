package com.anokmik.tripassistant.validator

import android.support.annotation.IntDef

@IntDef(NAME_LENGTH)
@Retention(AnnotationRetention.SOURCE)
annotation class Threshold

const val NAME_LENGTH = 5L