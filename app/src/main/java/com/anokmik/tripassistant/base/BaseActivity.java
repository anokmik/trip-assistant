package com.anokmik.tripassistant.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity implements OnInteractionListener {

    @IdRes
    private int containerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        T binding = DataBindingUtil.setContentView(this, getLayoutId());
        initBinding(binding);
    }

    @Override
    public void onLaunchActivity(Class<? extends Activity> cls) {
        launchActivity(cls);
    }

    @Override
    public void onReplace(Fragment fragment, String backStackTag) {
        replaceFragment(fragment, backStackTag);
    }

    @Override
    public void onShowDialog(DialogFragment dialogFragment) {
        showDialog(dialogFragment);
    }

    @Override
    public void onImmediatePopBack(int flags, String backStackTag) {
        popBackImmediate(flags, backStackTag);
    }

    protected void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(containerId, fragment).commit();
    }

    protected void replaceFragment(Fragment fragment, String backStackTag) {
        getSupportFragmentManager().beginTransaction().replace(containerId, fragment).addToBackStack(backStackTag).commit();
    }

    protected void showDialog(DialogFragment dialogFragment) {
        dialogFragment.show(getSupportFragmentManager(), null);
    }

    protected void popBackImmediate(int flags, String backStackTag) {
        getSupportFragmentManager().popBackStackImmediate(backStackTag, flags);
    }

    protected void launchActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this, cls));
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initBinding(T binding);

    protected void setContainerId(@IdRes int containerId) {
        this.containerId = containerId;
    }

}