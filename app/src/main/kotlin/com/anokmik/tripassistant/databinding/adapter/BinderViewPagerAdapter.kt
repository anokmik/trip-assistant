package com.anokmik.tripassistant.databinding.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BinderViewPagerAdapter<T>(
        private val inflater: LayoutInflater,
        private val items: List<T>,
        private val presenter: ViewHolderPresenter<T>
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, presenter.layoutId, container, false)
        val binderViewHolder = BinderViewHolder(binding, items[position], presenter, position)
        val rootView = binding.root
        rootView.tag = binderViewHolder
        container.addView(rootView)
        return rootView
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

    override fun getCount() = items.size

    override fun getItemPosition(`object`: Any?) = PagerAdapter.POSITION_NONE

    class BinderViewHolder<in T, B : ViewDataBinding>(
            private val binding: B,
            private val item: T,
            private val presenter: ViewHolderPresenter<T>,
            private val adapterPosition: Int
    ) : View.OnClickListener, View.OnLongClickListener, AdapterPositionProvider {

        override val itemPosition: Int
            get() = adapterPosition

        init {
            binding.setVariable(presenter.itemBindingId, item)
            binding.executePendingBindings()

            setAdapterPositionProvider(presenter.adapterPositionProviderBindingId)
            setItemClickListener(binding.root, presenter.itemClickListener)
            setItemLongClickListener(binding.root, presenter.itemLongClickListener)
            setMappedVariables()
        }

        override fun onClick(view: View) {
            presenter.itemClickListener?.onItemClick(item, itemPosition)
        }

        override fun onLongClick(view: View): Boolean {
            presenter.itemLongClickListener?.onItemLongClick(item, itemPosition)
            return true
        }

        private fun setAdapterPositionProvider(adapterPositionProviderBindingId: Int) {
            if (adapterPositionProviderBindingId > 0) {
                binding.setVariable(adapterPositionProviderBindingId, this)
            }
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