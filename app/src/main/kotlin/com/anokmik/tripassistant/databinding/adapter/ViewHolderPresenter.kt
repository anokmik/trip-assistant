package com.anokmik.tripassistant.databinding.adapter

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import java.util.*

class ViewHolderPresenter<in T> private constructor(
        @LayoutRes val layoutId: Int,
        @IdRes val itemBindingId: Int,
        val adapterPositionProviderBindingId: Int,
        val itemClickListener: OnItemClickListener<T>?,
        val itemLongClickListener: OnItemLongClickListener<T>?,
        val variablesMap: HashMap<Int, Any>?
) {

    class Builder<T>(@LayoutRes private val layoutId: Int, @IdRes private val itemBindingId: Int) {

        private var adapterPositionProviderBindingId: Int = 0

        private var onItemClickListener: OnItemClickListener<T>? = null

        private var onItemLongClickListener: OnItemLongClickListener<T>? = null

        private val variablesMap by lazy { HashMap<Int, Any>() }

        fun setAdapterPositionProviderBindingId(adapterPositionProviderBindingId: Int): Builder<T> {
            this.adapterPositionProviderBindingId = adapterPositionProviderBindingId
            return this
        }

        fun setItemClickListener(onItemClickListener: OnItemClickListener<T>): Builder<T> {
            this.onItemClickListener = onItemClickListener
            return this
        }

        fun setItemLongClickListener(onItemLongClickListener: OnItemLongClickListener<T>): Builder<T> {
            this.onItemLongClickListener = onItemLongClickListener
            return this
        }

        fun mapVariable(id: Int, variable: Any): Builder<T> {
            variablesMap.put(id, variable)
            return this
        }

        fun build(): ViewHolderPresenter<T> {
            return ViewHolderPresenter(layoutId, itemBindingId, adapterPositionProviderBindingId, onItemClickListener, onItemLongClickListener, variablesMap)
        }

    }

}