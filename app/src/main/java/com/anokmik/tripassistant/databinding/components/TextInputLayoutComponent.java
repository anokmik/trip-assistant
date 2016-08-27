package com.anokmik.tripassistant.databinding.components;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public final class TextInputLayoutComponent {

    @BindingAdapter({"error", "showError"})
    public void setError(TextInputLayout view, String error, boolean showError) {
        view.setError(showError ? error : null);
    }

    @BindingAdapter("editable")
    public void setEditable(TextInputLayout view, boolean isEditable) {
        view.setFocusable(isEditable);
        EditText editText = view.getEditText();
        if (editText != null) {
            editText.setEnabled(isEditable);
            editText.setFocusable(isEditable);
            editText.setFocusableInTouchMode(isEditable);
            Drawable background = editText.getBackground();
            if (background != null) {
                background.setAlpha(isEditable ? 255 : 0);
            }
        }
    }

}