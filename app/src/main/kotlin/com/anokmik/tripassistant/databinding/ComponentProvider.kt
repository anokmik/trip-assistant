package com.anokmik.tripassistant.databinding

import com.anokmik.tripassistant.databinding.components.*

class ComponentProvider : android.databinding.DataBindingComponent {

    override fun getImageViewComponent() = ImageViewComponent()

    override fun getRecyclerViewComponent() = RecyclerViewComponent()

    override fun getTextInputLayoutComponent() = TextInputLayoutComponent()

    override fun getViewPagerComponent() = ViewPagerComponent()

}