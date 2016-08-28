package com.anokmik.tripassistant.databinding.components

import android.databinding.BindingAdapter
import android.databinding.ObservableList
import android.support.v4.view.ViewPager
import android.view.LayoutInflater

import com.anokmik.tripassistant.databinding.adapter.BinderViewPagerAdapter
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter
import com.anokmik.tripassistant.databinding.list.ObservableListAttachStateChangeListener
import com.anokmik.tripassistant.databinding.list.ViewPagerListChangedCallback

class ViewPagerComponent {

    @BindingAdapter("items", "viewHolderPresenter")
    fun <T> setContent(view: ViewPager, list: List<T>, viewHolderPresenter: ViewHolderPresenter<T>) {
        val adapter = BinderViewPagerAdapter(LayoutInflater.from(view.context), list, viewHolderPresenter)
        view.adapter = adapter
        if (list is ObservableList<T>) {
            view.addOnAttachStateChangeListener(ObservableListAttachStateChangeListener(list, ViewPagerListChangedCallback(adapter)))
        }
    }

}