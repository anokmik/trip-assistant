package com.anokmik.tripassistant.util.contract.view;

import android.support.annotation.LayoutRes;

public interface ViewListItem {

    @LayoutRes
    int getRowItemLayoutId();

    int getItemBindingId();

    int getAdapterPositionProviderBindingId();

    int getItemListenerBindingId();

    int getItemIsEditingBindingId();

}