package com.anokmik.tripassistant.util

import android.text.TextUtils
import android.view.View

object ViewUtils {

    @JvmStatic
    fun hideIfEmpty(value: String?): Int = if (TextUtils.isEmpty(value)) View.GONE else View.VISIBLE

}