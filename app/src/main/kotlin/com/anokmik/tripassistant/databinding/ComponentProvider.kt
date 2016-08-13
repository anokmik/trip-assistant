package com.anokmik.tripassistant.databinding

import com.anokmik.tripassistant.databinding.components.RecyclerViewComponent
import com.anokmik.tripassistant.databinding.components.TextInputLayoutComponent
import com.anokmik.tripassistant.databinding.components.TextViewComponent

class ComponentProvider : android.databinding.DataBindingComponent {

    override fun getRecyclerViewComponent() = RecyclerViewComponent()

    override fun getTextInputLayoutComponent() = TextInputLayoutComponent()

    override fun getTextViewComponent() = TextViewComponent()

}