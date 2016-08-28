package com.anokmik.tripassistant.databinding.list

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView

class RecyclerViewListChangedCallback<T>(private val adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>) : ObservableList.OnListChangedCallback<ObservableList<T>>() {

    override fun onChanged(sender: ObservableList<T>?) = adapter.notifyDataSetChanged()

    override fun onItemRangeInserted(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) = adapter.notifyItemRangeChanged(positionStart, itemCount)

    override fun onItemRangeChanged(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) = adapter.notifyItemRangeInserted(positionStart, itemCount)

    override fun onItemRangeMoved(sender: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
        for (i in 0..itemCount - 1) {
            adapter.notifyItemMoved(fromPosition + i, toPosition + i)
        }
    }

    override fun onItemRangeRemoved(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) = adapter.notifyItemRangeRemoved(positionStart, itemCount)

}