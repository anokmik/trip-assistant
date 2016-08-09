package com.anokmik.tripassistant.databinding.components;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;

public final class TextInputLayoutComponent {

    @BindingAdapter({"error", "showError"})
    public void setButtonStateListBackground(TextInputLayout view, String error, boolean showError) {
        view.setError(showError ? error : null);
    }

}