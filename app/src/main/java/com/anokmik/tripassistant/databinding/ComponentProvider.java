package com.anokmik.tripassistant.databinding;

import com.anokmik.tripassistant.databinding.components.ImageViewComponent;
import com.anokmik.tripassistant.databinding.components.RecyclerViewComponent;
import com.anokmik.tripassistant.databinding.components.TextInputLayoutComponent;
import com.anokmik.tripassistant.databinding.components.ViewPagerComponent;

public final class ComponentProvider implements android.databinding.DataBindingComponent {

    @Override
    public ImageViewComponent getImageViewComponent() {
        return new ImageViewComponent();
    }

    @Override
    public RecyclerViewComponent getRecyclerViewComponent() {
        return new RecyclerViewComponent();
    }

    @Override
    public TextInputLayoutComponent getTextInputLayoutComponent() {
        return new TextInputLayoutComponent();
    }

    @Override
    public ViewPagerComponent getViewPagerComponent() {
        return new ViewPagerComponent();
    }

}