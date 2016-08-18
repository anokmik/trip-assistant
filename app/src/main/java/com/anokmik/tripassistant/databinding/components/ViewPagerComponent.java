package com.anokmik.tripassistant.databinding.components;

import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import com.anokmik.tripassistant.databinding.adapter.BinderViewPagerAdapter;
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter;

import java.util.List;

public final class ViewPagerComponent {

    @BindingAdapter({"items", "viewHolderPresenter"})
    public void setContent(ViewPager view, List<?> items, ViewHolderPresenter<?> viewHolderPresenter) {
        view.setAdapter(new BinderViewPagerAdapter(LayoutInflater.from(view.getContext()), items, viewHolderPresenter));
    }

}