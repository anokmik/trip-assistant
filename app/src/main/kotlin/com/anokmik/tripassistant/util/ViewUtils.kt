package com.anokmik.tripassistant.util

import android.databinding.ObservableBoolean
import android.text.TextUtils
import android.view.View

object ViewUtils {

    @JvmStatic
    fun show(value: ObservableBoolean) = show(value.get())

    @JvmStatic
    fun show(value: Boolean) = if (value) View.VISIBLE else View.GONE

    @JvmStatic
    fun hideIfEmpty(value: String?) = if (TextUtils.isEmpty(value)) View.GONE else View.VISIBLE

}