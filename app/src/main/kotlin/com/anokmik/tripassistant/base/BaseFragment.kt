package com.anokmik.tripassistant.base

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.*

abstract class BaseFragment<in T : ViewDataBinding> : Fragment() {

    private var supportActionBar: ActionBar? = null

    private var onInteractionListener: OnInteractionListener? = null

    protected abstract val displayHomeAsUp: Boolean

    protected abstract val layoutId: Int

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (activity is AppCompatActivity) {
            supportActionBar = activity.supportActionBar
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(displayHomeAsUp)
                show()
            }
        }
        if (activity is OnInteractionListener) {
            onInteractionListener = activity;
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<T>(inflater, layoutId, container, false)
        initBinding(binding)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (getOptionMenuResourceId() > 0) {
            inflater.inflate(getOptionMenuResourceId(), menu);
        }
    }

    @MenuRes
    protected open fun getOptionMenuResourceId(): Int = 0

    protected fun launchActivity(cls: Class<out Activity>) = onInteractionListener?.onLaunchActivity(cls)

    protected fun replaceFragment(fragment: Fragment, backStackTag: String? = null) = onInteractionListener?.onReplace(fragment, backStackTag)

    protected fun immediatePopBack(flags: Int, backStackTag: String? = null) = onInteractionListener?.onImmediatePopBack(flags, backStackTag)

    protected fun setActionBarTitle(@StringRes resourceId: Int) = supportActionBar?.apply { setTitle(resourceId) }

    protected abstract fun initBinding(binding: T)

}