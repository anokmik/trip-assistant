package com.anokmik.tripassistant.databinding.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

public final class BinderRecyclerViewAdapter<T, B extends ViewDataBinding> extends RecyclerView.Adapter<BinderRecyclerViewAdapter.BinderViewHolder<T, B>> {

    private final LayoutInflater inflater;
    private final List<T> items;
    private final RowPresenter<T> presenter;

    public BinderRecyclerViewAdapter(LayoutInflater inflater, List<T> items, RowPresenter<T> presenter) {
        this.inflater = inflater;
        this.items = items;
        this.presenter = presenter;
    }

    @Override
    public BinderViewHolder<T, B> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BinderViewHolder(DataBindingUtil.inflate(inflater, presenter.getLayoutId(), parent, false), presenter);
    }

    @Override
    public void onBindViewHolder(BinderViewHolder holder, int position) {
        holder.bindItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    protected static class BinderViewHolder<T, B extends ViewDataBinding> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final B binding;

        private final RowPresenter<T> presenter;

        private T item;

        public BinderViewHolder(B binding, RowPresenter<T> presenter) {
            super(binding.getRoot());
            this.binding = binding;
            this.presenter = presenter;
            setItemClickListener(itemView, presenter.getItemClickListener());
            setItemLongClickListener(itemView, presenter.getItemLongClickListener());
            setMappedVariables();
        }

        private void bindItem(T item) {
            this.item = item;
            binding.setVariable(presenter.getItemBindingId(), item);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            presenter.getItemClickListener().onItemClick(item, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            presenter.getItemLongClickListener().onItemLongClick(item, getAdapterPosition());
            return true;
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