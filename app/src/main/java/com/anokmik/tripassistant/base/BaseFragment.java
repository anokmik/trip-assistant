package com.anokmik.tripassistant.base;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    private ActionBar supportActionBar;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(displayHomeAsUp());
            supportActionBar.show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        T binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        initBinding(binding);
        return binding.getRoot();
    }

    protected void setActionBarTitle(@StringRes int resourceId) {
        if (supportActionBar != null) {
            supportActionBar.setTitle(resourceId);
        }
    }

    protected abstract boolean displayHomeAsUp();

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initBinding(T binding);

}