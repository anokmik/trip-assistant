package com.anokmik.tripassistant.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    private ActionBar supportActionBar;

    private T binding;

    private OnInteractionListener onInteractionListener;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof AppCompatActivity) {
            supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
        }
        if (activity instanceof OnInteractionListener) {
            onInteractionListener = (OnInteractionListener) activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        initBinding(binding);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (getOptionMenuResourceId() > 0) {
            inflater.inflate(getOptionMenuResourceId(), menu);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(displayHomeAsUp());
            supportActionBar.setTitle(getTitleResourceId());
        }
    }

    public T getBinding() {
        return binding;
    }

    @MenuRes
    protected int getOptionMenuResourceId() {
        return 0;
    }

    protected void launchActivity(Class<? extends Activity> cls) {
        launchActivity(new Intent(getContext(), cls));
    }

    protected void launchActivity(Intent intent) {
        if (onInteractionListener != null) {
            onInteractionListener.onLaunchActivity(intent);
        }
    }

    protected void launchActivity(Intent intent, int requestCode) {
        if (onInteractionListener != null) {
            onInteractionListener.onLaunchActivity(intent, requestCode);
        }
    }

    protected void replaceFragment(Fragment fragment, String backStackTag) {
        if (onInteractionListener != null) {
            onInteractionListener.onReplace(fragment, backStackTag);
        }
    }

    protected void showDialog(DialogFragment dialogFragment) {
        if (onInteractionListener != null) {
            onInteractionListener.onShowDialog(dialogFragment);
        }
    }

    protected void immediatePopBack(int flags, String backStackTag) {
        if (onInteractionListener != null) {
            onInteractionListener.onImmediatePopBack(flags, backStackTag);
        }
    }

    protected abstract boolean displayHomeAsUp();

    @StringRes
    protected abstract int getTitleResourceId();

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initBinding(T binding);

}