package com.anokmik.tripassistant.databinding.components

import android.databinding.BindingAdapter
import android.support.design.widget.TextInputLayout
import android.text.TextUtils

class TextInputLayoutComponent {

    @BindingAdapter("error", "showError")
    fun setError(view: TextInputLayout, error: String, showError: Boolean) {
        view.error = if (showError) error else null
    }

    @BindingAdapter("editable")
    fun setEditable(view: TextInputLayout, isEditable: Boolean) {
        view.isFocusable = isEditable
        view.editText?.apply {
            isCursorVisible = isEditable
            isFocusable = isEditable
            isFocusableInTouchMode = isEditable
            background?.apply {
                alpha = if (isEditable) 255 else 0
            }
            if (isEditable) {
                val editableText = text
                if (!TextUtils.isEmpty(editableText) && (selectionStart == 0) && (selectionEnd == 0)) {
                    setSelection(editableText.length)
                }
            }
        }
    }

}