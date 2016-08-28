package com.anokmik.tripassistant.databinding

import android.databinding.BindingConversion
import android.databinding.ObservableBoolean

object BindingConverters {

    @JvmStatic
    @BindingConversion
    fun convertObservableToBoolean(observableBoolean: ObservableBoolean): Boolean {
        return observableBoolean.get()
    }

}