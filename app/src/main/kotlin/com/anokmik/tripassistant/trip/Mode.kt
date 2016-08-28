package com.anokmik.tripassistant.trip

import android.support.annotation.IntDef

@IntDef(ADD, VIEW)
@Retention(AnnotationRetention.SOURCE)
annotation class Mode

const val ADD = 1212L
const val VIEW = 1221L