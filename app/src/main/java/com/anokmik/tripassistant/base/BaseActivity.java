package com.anokmik.tripassistant.base;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(containerId);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onLaunchActivity(Intent intent) {
        launchActivity(intent);
    }

    @Override
    public void onLaunchActivity(Intent intent, int requestCode) {
        launchActivity(intent, requestCode);
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

    protected void launchActivity(Intent intent) {
        startActivity(intent);
    }

    protected void launchActivity(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
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

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initBinding(T binding);

    protected void setContainerId(@IdRes int containerId) {
        this.containerId = containerId;
    }

}