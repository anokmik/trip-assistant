package com.anokmik.tripassistant.databinding.components

import android.databinding.BindingAdapter
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import com.anokmik.tripassistant.databinding.adapter.BinderRecyclerViewAdapter
import com.anokmik.tripassistant.databinding.adapter.RowPresenter

class RecyclerViewComponent {

    @BindingAdapter("layoutManager")
    fun setLayoutManager(view: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
        view.layoutManager = layoutManager
    }

    @BindingAdapter("items", "rowPresenter")
    fun <T> setContent(view: RecyclerView, items: List<T>, rowPresenter: RowPresenter<T>) {
        view.adapter = BinderRecyclerViewAdapter<T, ViewDataBinding>(LayoutInflater.from(view.context), items, rowPresenter)
    }

}