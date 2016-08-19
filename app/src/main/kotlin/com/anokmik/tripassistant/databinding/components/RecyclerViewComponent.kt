package com.anokmik.tripassistant.databinding.components

import android.databinding.BindingAdapter
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import com.anokmik.tripassistant.databinding.adapter.BinderRecyclerViewAdapter
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter

class RecyclerViewComponent {

    @BindingAdapter("items", "viewHolderPresenter")
    fun <T> setContent(view: RecyclerView, items: List<T>, viewHolderPresenter: ViewHolderPresenter<T>) {
        view.adapter = BinderRecyclerViewAdapter<T, ViewDataBinding>(LayoutInflater.from(view.context), items, viewHolderPresenter)
    }

}