package com.anokmik.tripassistant.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

public interface OnInteractionListener {

    void onLaunchActivity(Class<? extends Activity> cls);

    void onReplace(Fragment fragment, String backStackTag);

    void onImmediatePopBack(int flags, String backStackTag);

}