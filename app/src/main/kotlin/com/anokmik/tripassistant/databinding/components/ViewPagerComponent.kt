package com.anokmik.tripassistant.databinding.components

import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater

import com.anokmik.tripassistant.databinding.adapter.BinderViewPagerAdapter
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter

class ViewPagerComponent {

    @BindingAdapter("items", "viewHolderPresenter")
    fun <T> setContent(view: ViewPager, items: List<T>, viewHolderPresenter: ViewHolderPresenter<T>) {
        view.adapter = BinderViewPagerAdapter(LayoutInflater.from(view.context), items, viewHolderPresenter)
    }

}