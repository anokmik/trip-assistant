package com.anokmik.tripassistant.databinding.list;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

public final class RecyclerViewListChangedCallback extends ObservableList.OnListChangedCallback {

    private final RecyclerView.Adapter adapter;

    public RecyclerViewListChangedCallback(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onChanged(ObservableList sender) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
        adapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
        adapter.notifyItemRangeInserted(positionStart, itemCount);
    }

    @Override
    public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            adapter.notifyItemMoved(fromPosition + i, toPosition + i);
        }
    }

    @Override
    public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
        adapter.notifyItemRangeRemoved(positionStart, itemCount);
    }

}