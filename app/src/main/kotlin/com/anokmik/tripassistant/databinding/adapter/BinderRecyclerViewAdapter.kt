package com.anokmik.tripassistant.databinding.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BinderRecyclerViewAdapter<T, B : ViewDataBinding>(private val inflater: LayoutInflater, private val items: List<T>, private val presenter: ViewHolderPresenter<T>) : RecyclerView.Adapter<BinderRecyclerViewAdapter.BinderViewHolder<T, B>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinderViewHolder<T, B> = BinderViewHolder(DataBindingUtil.inflate<ViewDataBinding>(inflater, presenter.layoutId, parent, false), presenter)

    override fun onBindViewHolder(holder: BinderViewHolder<T, B>, position: Int) = holder.bindItem(items[position])

    override fun getItemCount() = items.size

    class BinderViewHolder<in T, in B : ViewDataBinding>(private val binding: B, private val presenter: ViewHolderPresenter<T>) : RecyclerView.ViewHolder(binding.root), View.OnClickListener, View.OnLongClickListener {

        private var item: T? = null

        init {
            setItemClickListener(itemView, presenter.itemClickListener)
            setItemLongClickListener(itemView, presenter.itemLongClickListener)
            setMappedVariables()
        }

        fun bindItem(item: T?) {
            this.item = item
            binding.setVariable(presenter.itemBindingId, item)
            binding.executePendingBindings()
        }

        override fun onClick(view: View) {
            presenter.itemClickListener?.onItemClick(item, adapterPosition)
        }

        override fun onLongClick(view: View): Boolean {
            presenter.itemLongClickListener?.onItemLongClick(item, adapterPosition)
            return true
        }

        private fun setItemClickListener(view: View, onItemClickListener: OnItemClickListener<T>?) {
            onItemClickListener?.let {
                view.setOnClickListener(this)
            }
        }

        private fun setItemLongClickListener(view: View, onItemLongClickListener: OnItemLongClickListener<T>?) {
            onItemLongClickListener?.let {
                view.setOnLongClickListener(this)
            }
        }

        private fun setMappedVariables() {
            presenter.variablesMap?.apply {
                if (!isEmpty()) {
                    for ((key, value) in entries) {
                        binding.setVariable(key, value)
                    }
                }
            }
        }

    }

}