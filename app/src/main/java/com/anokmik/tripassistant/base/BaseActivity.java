package com.anokmik.tripassistant.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    @IdRes
    private int containerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        T binding = DataBindingUtil.setContentView(this, getLayoutId());
        initBinding(binding);
    }

    protected void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(containerId, fragment).commit();
    }

    protected void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(containerId, fragment).addToBackStack(null).commit();
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