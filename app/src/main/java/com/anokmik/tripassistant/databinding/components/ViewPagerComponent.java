package com.anokmik.tripassistant.databinding.components;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import com.anokmik.tripassistant.databinding.adapter.BinderViewPagerAdapter;
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter;
import com.anokmik.tripassistant.databinding.list.ObservableListAttachStateChangeListener;
import com.anokmik.tripassistant.databinding.list.ViewPagerListChangedCallback;

import java.util.List;

public final class ViewPagerComponent {

    @BindingAdapter({"items", "viewHolderPresenter"})
    public void setContent(ViewPager view, List<?> list, ViewHolderPresenter<?> viewHolderPresenter) {
        BinderViewPagerAdapter adapter = new BinderViewPagerAdapter(LayoutInflater.from(view.getContext()), list, viewHolderPresenter);
        view.setAdapter(adapter);
        if (list instanceof ObservableList) {
            view.addOnAttachStateChangeListener(new ObservableListAttachStateChangeListener((ObservableList) list, new ViewPagerListChangedCallback(adapter)));
        }
    }

}