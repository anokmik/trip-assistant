package com.anokmik.tripassistant.base

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.MenuRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.*

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    private var supportActionBar: ActionBar? = null

    private var onInteractionListener: OnInteractionListener? = null

    protected lateinit var binding: T

    protected abstract val displayHomeAsUp: Boolean

    protected abstract val layoutId: Int

    protected abstract val titleResourceId: Int

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (activity is AppCompatActivity) {
            supportActionBar = activity.supportActionBar
        }
        if (activity is OnInteractionListener) {
            onInteractionListener = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<T>(inflater, layoutId, container, false)
        initBinding(binding)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (getOptionMenuResourceId() > 0) {
            inflater.inflate(getOptionMenuResourceId(), menu)
        }
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(displayHomeAsUp)
            setTitle(titleResourceId)
        }
    }

    @MenuRes
    protected open fun getOptionMenuResourceId(): Int = 0

    protected fun launchActivity(cls: Class<out Activity>) = launchActivity(Intent(context, cls))

    protected fun launchActivity(intent: Intent?) = onInteractionListener?.onLaunchActivity(intent)

    protected fun launchActivity(intent: Intent?, requestCode: Int) = onInteractionListener?.onLaunchActivity(intent, requestCode)

    protected fun replaceFragment(fragment: Fragment, backStackTag: String? = null) = onInteractionListener?.onReplace(fragment, backStackTag)

    protected fun showDialog(dialogFragment: DialogFragment) = onInteractionListener?.onShowDialog(dialogFragment)

    protected fun immediatePopBack(flags: Int, backStackTag: String? = null) = onInteractionListener?.onImmediatePopBack(flags, backStackTag)

    protected abstract fun initBinding(binding: T)

}