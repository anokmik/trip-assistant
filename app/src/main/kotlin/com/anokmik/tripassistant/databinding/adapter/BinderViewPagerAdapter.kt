package com.anokmik.tripassistant.databinding.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BinderViewPagerAdapter<T>(private val inflater: LayoutInflater, private val items: List<T>, private val presenter: ViewHolderPresenter<T>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, presenter.layoutId, container, false)
        val binderViewHolder = BinderViewHolder(binding, presenter, position)
        binderViewHolder.bindItem(items[position])
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

    override fun getCount() = items.size

    class BinderViewHolder<in T, B : ViewDataBinding>(private val binding: B, private val presenter: ViewHolderPresenter<T>, private val adapterPosition: Int) : View.OnClickListener, View.OnLongClickListener {

        private var item: T? = null

        init {
            setItemClickListener(binding.root, presenter.itemClickListener)
            setItemLongClickListener(binding.root, presenter.itemLongClickListener)
            setMappedVariables()
        }

        fun bindItem(item: T) {
            this.item = item
            binding.setVariable(presenter.itemBindingId, item)
            binding.executePendingBindings()
        }

        override fun onClick(view: View) {
            presenter.itemClickListener!!.onItemClick(item, adapterPosition)
        }

        override fun onLongClick(view: View): Boolean {
            presenter.itemLongClickListener!!.onItemLongClick(item, adapterPosition)
            return true
        }

        private fun setItemClickListener(view: View, onItemClickListener: OnItemClickListener<T>?) {
            if (onItemClickListener != null) {
                view.setOnClickListener(this)
            }
        }

        private fun setItemLongClickListener(view: View, onItemLongClickListener: OnItemLongClickListener<T>?) {
            if (onItemLongClickListener != null) {
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