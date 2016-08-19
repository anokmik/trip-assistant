package com.anokmik.tripassistant.base

import android.app.Activity
import android.content.Intent
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment

interface OnInteractionListener {

    fun onLaunchActivity(intent: Intent?)

    fun onReplace(fragment: Fragment, backStackTag: String?)

    fun onShowDialog(dialogFragment: DialogFragment)

    fun onImmediatePopBack(flags: Int, backStackTag: String?)

}