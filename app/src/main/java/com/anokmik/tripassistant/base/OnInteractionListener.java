package com.anokmik.tripassistant.base;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

public interface OnInteractionListener {

    void onLaunchActivity(Intent intent);

    void onReplace(Fragment fragment, String backStackTag);

    void onShowDialog(DialogFragment dialogFragment);

    void onImmediatePopBack(int flags, String backStackTag);

}