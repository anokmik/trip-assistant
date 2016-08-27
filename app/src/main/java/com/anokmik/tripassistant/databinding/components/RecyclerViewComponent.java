package com.anokmik.tripassistant.databinding.components;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.anokmik.tripassistant.databinding.adapter.BinderRecyclerViewAdapter;
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter;
import com.anokmik.tripassistant.databinding.list.ObservableListAttachStateChangeListener;
import com.anokmik.tripassistant.databinding.list.RecyclerViewListChangedCallback;

import java.util.List;

public final class RecyclerViewComponent {

    @BindingAdapter({"items", "viewHolderPresenter"})
    public void setContent(final RecyclerView view, List<?> list, ViewHolderPresenter<?> viewHolderPresenter) {
        BinderRecyclerViewAdapter adapter = new BinderRecyclerViewAdapter(LayoutInflater.from(view.getContext()), list, viewHolderPresenter);
        view.setAdapter(adapter);
        if (list instanceof ObservableList) {
            view.addOnAttachStateChangeListener(new ObservableListAttachStateChangeListener((ObservableList) list, new RecyclerViewListChangedCallback(adapter)));
        }
    }

    @BindingAdapter("decoration")
    public void setDecoration(RecyclerView view, RecyclerView.ItemDecoration decoration) {
        view.addItemDecoration(decoration);
    }

    @BindingAdapter("decoration")
    public void setDecoration(RecyclerView view, RecyclerView.ItemDecoration[] decorations) {
        for (RecyclerView.ItemDecoration decoration : decorations) {
            view.addItemDecoration(decoration);
        }
    }

}