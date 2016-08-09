package com.anokmik.tripassistant.databinding;

import com.anokmik.tripassistant.databinding.components.RecyclerViewComponent;
import com.anokmik.tripassistant.databinding.components.TextInputLayoutComponent;
import com.anokmik.tripassistant.databinding.components.TextViewComponent;

public final class ComponentProvider implements android.databinding.DataBindingComponent {

    @Override
    public RecyclerViewComponent getRecyclerViewComponent() {
        return new RecyclerViewComponent();
    }

    @Override
    public TextInputLayoutComponent getTextInputLayoutComponent() {
        return new TextInputLayoutComponent();
    }

    @Override
    public TextViewComponent getTextViewComponent() {
        return new TextViewComponent();
    }

}