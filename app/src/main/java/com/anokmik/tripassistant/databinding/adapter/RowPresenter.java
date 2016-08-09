package com.anokmik.tripassistant.databinding.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class RowPresenter<T> {

    @LayoutRes
    private final int layoutId;

    @IdRes
    private final int itemBindingId;

    private final OnItemClickListener<T> onItemClickListener;

    private final OnItemLongClickListener<T> onItemLongClickListener;

    private final HashMap<Integer, Object> variablesMap;

    private RowPresenter(@LayoutRes int layoutId, @IdRes int itemBindingId, OnItemClickListener<T> onItemClickListener,
                           OnItemLongClickListener<T> onItemLongClickListener, HashMap<Integer, Object> variablesMap) {
        this.layoutId = layoutId;
        this.itemBindingId = itemBindingId;
        this.onItemClickListener = onItemClickListener;
        this.onItemLongClickListener = onItemLongClickListener;
        this.variablesMap = variablesMap;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public int getItemBindingId() {
        return itemBindingId;
    }

    public OnItemClickListener<T> getItemClickListener() {
        return onItemClickListener;
    }

    public OnItemLongClickListener<T> getItemLongClickListener() {
        return onItemLongClickListener;
    }

    public boolean isVariablesMapEmpty() {
        return (variablesMap == null) || variablesMap.isEmpty();
    }

    public Set<Map.Entry<Integer, Object>> getVariablesMapEntries() {
        return variablesMap.entrySet();
    }

    public static class Builder<T> {

        @LayoutRes
        private final int layoutId;

        @IdRes
        private final int itemBindingId;

        private OnItemClickListener<T> onItemClickListener;

        private OnItemLongClickListener<T> onItemLongClickListener;

        private HashMap<Integer, Object> variablesMap;

        public Builder(@LayoutRes int layoutId, @IdRes int itemBindingId) {
            this.layoutId = layoutId;
            this.itemBindingId = itemBindingId;
        }

        public Builder<T> setItemClickListener(OnItemClickListener<T> onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
            return this;
        }

        public Builder<T> setItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
            this.onItemLongClickListener = onItemLongClickListener;
            return this;
        }

        public Builder<T> mapVariable(int id, Object variable) {
            initVariablesMap();
            variablesMap.put(id, variable);
            return this;
        }

        public RowPresenter<T> build() {
            return new RowPresenter<>(layoutId, itemBindingId, onItemClickListener, onItemLongClickListener, variablesMap);
        }

        private void initVariablesMap() {
            if (variablesMap == null) {
                variablesMap = new HashMap<>();
            }
        }

    }

}