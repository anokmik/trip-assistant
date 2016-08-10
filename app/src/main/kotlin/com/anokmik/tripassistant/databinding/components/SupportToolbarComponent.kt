package com.anokmik.tripassistant.databinding.components

import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.support.v7.widget.Toolbar

@BindingMethods(
        BindingMethod(type = Toolbar::class, attribute = "onMenuItemClick", method = "setOnMenuItemClickListener"),
        BindingMethod(type = Toolbar::class, attribute = "onNavigationClick", method = "setNavigationOnClickListener")
)
class SupportToolbarComponent