package com.anokmik.tripassistant.databinding.list;

import android.databinding.ObservableList;
import android.view.View;

public final class ObservableListAttachStateChangeListener implements View.OnAttachStateChangeListener {

    private final ObservableList observableList;

    private final ObservableList.OnListChangedCallback onListChangedCallback;

    public ObservableListAttachStateChangeListener(ObservableList observableList, ObservableList.OnListChangedCallback onListChangedCallback) {
        this.onListChangedCallback = onListChangedCallback;
        this.observableList = observableList;

        observableList.addOnListChangedCallback(onListChangedCallback);
    }

    @Override
    public void onViewAttachedToWindow(View view) {

    }

    @Override
    public void onViewDetachedFromWindow(View view) {
        observableList.removeOnListChangedCallback(onListChangedCallback);
    }

}