package com.anokmik.tripassistant.databinding.components;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public final class ImageViewComponent {

    @BindingAdapter("android:src")
    public void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext()).load(imageUrl).into(view);
    }

}