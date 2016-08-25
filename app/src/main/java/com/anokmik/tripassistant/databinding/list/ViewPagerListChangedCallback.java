package com.anokmik.tripassistant.databinding.list;

import android.databinding.ObservableList;
import android.support.v4.view.PagerAdapter;

public final class ViewPagerListChangedCallback extends ObservableList.OnListChangedCallback {

    private final PagerAdapter adapter;

    public ViewPagerListChangedCallback(PagerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onChanged(ObservableList sender) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
        adapter.notifyDataSetChanged();
    }

}