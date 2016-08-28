package com.anokmik.tripassistant.base

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
    protected var containerId = 0

    @LayoutRes
    protected var layoutId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(DataBindingUtil.setContentView<T>(this, layoutId))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data);
        supportFragmentManager?.findFragmentById(containerId).apply {
            onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onLaunchActivity(intent: Intent?) = launchActivity(intent)

    override fun onReplace(fragment: Fragment, backStackTag: String?) {
        replaceFragment(fragment, backStackTag)
    }

    override fun onShowDialog(dialogFragment: DialogFragment) = showDialog(dialogFragment)

    override fun onImmediatePopBack(flags: Int, backStackTag: String?) {
        popBackImmediate(flags, backStackTag)
    }

    protected fun addFragment(fragment: Fragment) = supportFragmentManager.add(fragment, containerId)

    protected fun replaceFragment(fragment: Fragment, backStackTag: String? = null) = supportFragmentManager.replace(fragment, containerId, backStackTag)

    protected fun showDialog(dialogFragment: DialogFragment) = dialogFragment.show(supportFragmentManager, null)

    protected fun popBackImmediate(flags: Int = FragmentManager.POP_BACK_STACK_INCLUSIVE, backStackTag: String? = null) = supportFragmentManager.popBackImmediate(backStackTag, flags)

    protected fun launchActivity(intent: Intent?) = startActivity(intent)

    protected abstract fun initBinding(binding: T)

}