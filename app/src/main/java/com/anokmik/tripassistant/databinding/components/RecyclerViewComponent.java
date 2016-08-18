package com.anokmik.tripassistant.databinding.components;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.anokmik.tripassistant.databinding.adapter.BinderRecyclerViewAdapter;
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter;

import java.util.List;

public final class RecyclerViewComponent {

    @BindingAdapter({"items", "viewHolderPresenter"})
    public void setContent(RecyclerView view, List<?> items, ViewHolderPresenter<?> viewHolderPresenter) {
        view.setAdapter(new BinderRecyclerViewAdapter(LayoutInflater.from(view.getContext()), items, viewHolderPresenter));
    }

}