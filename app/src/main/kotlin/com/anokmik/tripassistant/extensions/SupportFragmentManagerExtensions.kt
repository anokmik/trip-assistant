package com.anokmik.tripassistant.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

fun FragmentManager.add(fragment: Fragment, containerId: Int) = beginTransaction().add(containerId, fragment).commit()

fun FragmentManager.replace(fragment: Fragment, containerId: Int, backStackTag: String? = null) = beginTransaction().replace(containerId, fragment).addToBackStack(backStackTag).commit()

fun FragmentManager.popBackImmediate(backStackTag: String? = null, flags: Int = FragmentManager.POP_BACK_STACK_INCLUSIVE) = popBackStackImmediate(backStackTag, flags)