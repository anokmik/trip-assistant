package com.anokmik.tripassistant.base

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.anokmik.tripassistant.extensions.add
import com.anokmik.tripassistant.extensions.popBackImmediate
import com.anokmik.tripassistant.extensions.replace

abstract class BaseActivity<in T : ViewDataBinding> : AppCompatActivity(), OnInteractionListener {

    @IdRes
    protected var containerId: Int = 0

    @LayoutRes
    protected var layoutId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(DataBindingUtil.setContentView<T>(this, layoutId))
    }

    override fun onLaunchActivity(cls: Class<out Activity>) = launchActivity(cls)

    override fun onReplace(fragment: Fragment, backStackTag: String?) {
        replaceFragment(fragment, backStackTag)
    }

    override fun onShowDialog(dialogFragment: DialogFragment) {
        showDialog(dialogFragment)
    }

    override fun onImmediatePopBack(flags: Int, backStackTag: String?) {
        popBackImmediate(flags, backStackTag)
    }

    protected fun addFragment(fragment: Fragment) = supportFragmentManager.add(fragment, containerId)

    protected fun replaceFragment(fragment: Fragment, backStackTag: String? = null) = supportFragmentManager.replace(fragment, containerId, backStackTag)

    protected fun showDialog(dialogFragment: DialogFragment) {
        dialogFragment.show(supportFragmentManager, null);
    }

    protected fun popBackImmediate(flags: Int = FragmentManager.POP_BACK_STACK_INCLUSIVE, backStackTag: String? = null) = supportFragmentManager.popBackImmediate(backStackTag, flags)

    protected fun launchActivity(cls: Class<out Activity>) = startActivity(Intent(this, cls))

    protected abstract fun initBinding(binding: T)

}