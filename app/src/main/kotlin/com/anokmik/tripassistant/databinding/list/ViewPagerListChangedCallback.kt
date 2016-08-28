package com.anokmik.tripassistant.databinding.list

import android.databinding.ObservableList
import android.support.v4.view.PagerAdapter

class ViewPagerListChangedCallback<T>(private val adapter: PagerAdapter) : ObservableList.OnListChangedCallback<ObservableList<T>>() {

    override fun onChanged(sender: ObservableList<T>?) = adapter.notifyDataSetChanged()

    override fun onItemRangeChanged(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) = adapter.notifyDataSetChanged()

    override fun onItemRangeInserted(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) = adapter.notifyDataSetChanged()

    override fun onItemRangeMoved(sender: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) = adapter.notifyDataSetChanged()

    override fun onItemRangeRemoved(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) = adapter.notifyDataSetChanged()

}