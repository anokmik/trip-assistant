package com.anokmik.tripassistant.databinding.components;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.anokmik.tripassistant.databinding.adapter.BinderRecyclerViewAdapter;
import com.anokmik.tripassistant.databinding.adapter.RowPresenter;

import java.util.List;

public final class RecyclerViewComponent {

    @BindingAdapter("layoutManager")
    public void setLayoutManager(RecyclerView view, RecyclerView.LayoutManager layoutManager) {
        view.setLayoutManager(layoutManager);
    }

    @BindingAdapter({"items", "rowPresenter"})
    public void setContent(RecyclerView view, List<?> items, RowPresenter<?> rowPresenter) {
        view.setAdapter(new BinderRecyclerViewAdapter(LayoutInflater.from(view.getContext()), items, rowPresenter));
    }

}