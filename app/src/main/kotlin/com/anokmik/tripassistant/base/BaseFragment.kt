package com.anokmik.tripassistant.base

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment<in T : ViewDataBinding> : Fragment() {

    protected abstract val displayHomeAsUp: Boolean

    protected abstract val layoutId: Int

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        (getActivity() as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(displayHomeAsUp)
            show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<T>(inflater, layoutId, container, false)
        initBinding(binding)
        return binding.root
    }

    protected fun setActionBarTitle(@StringRes resourceId: Int) {
        (getActivity() as AppCompatActivity).supportActionBar?.apply {
            setTitle(resourceId)
        }
    }

    protected abstract fun initBinding(binding: T)

}