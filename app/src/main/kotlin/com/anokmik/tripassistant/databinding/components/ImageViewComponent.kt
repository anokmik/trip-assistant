package com.anokmik.tripassistant.databinding.components

import android.databinding.BindingAdapter
import android.widget.ImageView

import com.squareup.picasso.Picasso

class ImageViewComponent {

    @BindingAdapter("android:src")
    fun loadImage(view: ImageView, imageUrl: String) {
        Picasso.with(view.context).load(imageUrl).into(view)
    }

}