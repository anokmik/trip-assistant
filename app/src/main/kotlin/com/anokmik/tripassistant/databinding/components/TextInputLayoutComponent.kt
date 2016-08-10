package com.anokmik.tripassistant.databinding.components

import android.databinding.BindingAdapter
import android.support.design.widget.TextInputLayout

class TextInputLayoutComponent {

    @BindingAdapter("error", "showError")
    fun setButtonStateListBackground(view: TextInputLayout, error: String, showError: Boolean) {
        view.error = if (showError) error else null
    }

}