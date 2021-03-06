package com.anokmik.tripassistant.databinding.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

public final class BinderViewPagerAdapter<T> extends PagerAdapter {

    private final LayoutInflater inflater;
    private final List<T> items;
    private final ViewHolderPresenter<T> presenter;

    public BinderViewPagerAdapter(LayoutInflater inflater, List<T> items, ViewHolderPresenter<T> presenter) {
        this.inflater = inflater;
        this.items = items;
        this.presenter = presenter;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, presenter.getLayoutId(), container, false);
        BinderViewHolder binderViewHolder = new BinderViewHolder(binding, items.get(position), presenter, position);
        View rootView = binding.getRoot();
        rootView.setTag(binderViewHolder);
        container.addView(rootView);
        return rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return (items != null) ? items.size() : 0;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    protected static class BinderViewHolder<T, B extends ViewDataBinding> implements View.OnClickListener, View.OnLongClickListener, AdapterPositionProvider {

        private final B binding;

        private final ViewHolderPresenter<T> presenter;

        private final int adapterPosition;

        private T item;

        public BinderViewHolder(B binding, T item, ViewHolderPresenter<T> presenter, int adapterPosition) {
            this.binding = binding;
            this.presenter = presenter;
            this.adapterPosition = adapterPosition;
            this.item = item;

            binding.setVariable(presenter.getItemBindingId(), item);
            binding.executePendingBindings();

            setAdapterPositionProvider(presenter.getAdapterPositionProviderBindingId());
            setItemClickListener(binding.getRoot(), presenter.getItemClickListener());
            setItemLongClickListener(binding.getRoot(), presenter.getItemLongClickListener());
            setMappedVariables();
        }

        @Override
        public int getItemPosition() {
            return adapterPosition;
        }

        @Override
        public void onClick(View view) {
            presenter.getItemClickListener().onItemClick(item, getItemPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            presenter.getItemLongClickListener().onItemLongClick(item, getItemPosition());
            return true;
        }

        private void setAdapterPositionProvider(int adapterPositionProviderBindingId) {
            if (adapterPositionProviderBindingId > 0) {
                binding.setVariable(adapterPositionProviderBindingId, this);
            }
        }

        private void setItemClickListener(View view, OnItemClickListener<T> onItemClickListener) {
            if (onItemClickListener != null) {
                view.setOnClickListener(this);
            }
        }

        private void setItemLongClickListener(View view, OnItemLongClickListener<T> onItemLongClickListener) {
            if (onItemLongClickListener != null) {
                view.setOnLongClickListener(this);
            }
        }

        private void setMappedVariables() {
            if (!presenter.isVariablesMapEmpty()) {
                for (Map.Entry<Integer, Object> entry : presenter.getVariablesMapEntries()) {
                    binding.setVariable(entry.getKey(), entry.getValue());
                }
            }
        }

    }

}