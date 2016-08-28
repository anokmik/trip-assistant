package com.anokmik.tripassistant.databinding.components

import android.databinding.BindingAdapter
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import com.anokmik.tripassistant.databinding.adapter.BinderRecyclerViewAdapter
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter
import com.anokmik.tripassistant.databinding.list.ObservableListAttachStateChangeListener
import com.anokmik.tripassistant.databinding.list.RecyclerViewListChangedCallback

class RecyclerViewComponent {

    @BindingAdapter("items", "viewHolderPresenter")
    fun <T> setContent(view: RecyclerView, list: List<T>, viewHolderPresenter: ViewHolderPresenter<T>) {
        val adapter = BinderRecyclerViewAdapter<T, ViewDataBinding>(LayoutInflater.from(view.context), list, viewHolderPresenter)
        view.adapter = adapter
        if (list is ObservableList<T>) {
            view.addOnAttachStateChangeListener(ObservableListAttachStateChangeListener(list, RecyclerViewListChangedCallback(adapter)))
        }
    }

    @BindingAdapter("decoration")
    fun setDecoration(view: RecyclerView, decoration: RecyclerView.ItemDecoration) {
        view.addItemDecoration(decoration)
    }

    @BindingAdapter("decoration")
    fun setDecoration(view: RecyclerView, decorations: Array<RecyclerView.ItemDecoration>) {
        for (decoration in decorations) {
            view.addItemDecoration(decoration)
        }
    }

}