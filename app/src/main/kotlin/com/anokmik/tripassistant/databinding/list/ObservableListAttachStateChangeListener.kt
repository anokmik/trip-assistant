package com.anokmik.tripassistant.databinding.list

import android.databinding.ObservableList
import android.view.View

class ObservableListAttachStateChangeListener<T>(
        private val observableList: ObservableList<T>,
        private val onListChangedCallback: ObservableList.OnListChangedCallback<out ObservableList<T>>
) : View.OnAttachStateChangeListener {

    init {
        observableList.addOnListChangedCallback(onListChangedCallback)
    }

    override fun onViewAttachedToWindow(view: View) {

    }

    override fun onViewDetachedFromWindow(view: View) {
        observableList.removeOnListChangedCallback(onListChangedCallback)
    }

}