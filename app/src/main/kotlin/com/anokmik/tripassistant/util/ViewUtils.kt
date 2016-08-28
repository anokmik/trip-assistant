package com.anokmik.tripassistant.util

import android.databinding.ObservableBoolean
import android.text.TextUtils
import android.view.View

import com.anokmik.tripassistant.trip.Mode
import com.anokmik.tripassistant.trip.VIEW

object ViewUtils {

    @JvmStatic
    fun showForViewMode(state: ObservableBoolean?, @Mode mode: Long) = show(stateValue(state?.get()) && isViewMode(mode))

    @JvmStatic
    fun showForViewMode(state: Boolean?, @Mode mode: Long) = show(stateValue(state) && isViewMode(mode))

    @JvmStatic
    fun show(state: ObservableBoolean?, value: String?) = show(state?.get(), value)

    @JvmStatic
    fun show(state: Boolean?, value: String?) = if (stateValue(state)) View.VISIBLE else show(value)

    @JvmStatic
    fun show(text: String?) = show(!TextUtils.isEmpty(text))

    @JvmStatic
    fun show(observableState: ObservableBoolean?) = show(observableState?.get())

    @JvmStatic
    fun show(state: Boolean?) = if (stateValue(state)) View.VISIBLE else View.GONE

    @JvmStatic
    private fun isViewMode(@Mode mode: Long) = (mode == VIEW)

    @JvmStatic
    private fun stateValue(state: Boolean?) = state ?: false

}