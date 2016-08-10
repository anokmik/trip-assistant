package com.anokmik.tripassistant.base

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<in T : ViewDataBinding> : AppCompatActivity() {

    @IdRes
    protected var containerId: Int = 0

    @LayoutRes
    protected var layoutId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(DataBindingUtil.setContentView<T>(this, layoutId))
    }

    protected fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(containerId, fragment).commit()
    }

    protected fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(containerId, fragment).addToBackStack(null).commit()
    }

    protected fun launchActivity(cls: Class<out Activity>) {
        startActivity(Intent(this, cls))
    }

    protected abstract fun initBinding(binding: T)

}