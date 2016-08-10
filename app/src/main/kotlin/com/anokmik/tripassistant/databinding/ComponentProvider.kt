package com.anokmik.tripassistant.databinding

import com.anokmik.tripassistant.databinding.components.RecyclerViewComponent
import com.anokmik.tripassistant.databinding.components.TextInputLayoutComponent
import com.anokmik.tripassistant.databinding.components.TextViewComponent

class ComponentProvider : android.databinding.DataBindingComponent {

    override fun getRecyclerViewComponent(): RecyclerViewComponent {
        return RecyclerViewComponent()
    }

    override fun getTextInputLayoutComponent(): TextInputLayoutComponent {
        return TextInputLayoutComponent()
    }

    override fun getTextViewComponent(): TextViewComponent {
        return TextViewComponent()
    }

}