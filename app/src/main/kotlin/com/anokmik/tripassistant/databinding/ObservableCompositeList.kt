package com.anokmik.tripassistant.databinding

import android.databinding.ListChangeRegistry
import android.databinding.ObservableList
import com.anokmik.persistence.util.AsyncCompositeList

class ObservableCompositeList<T>(private val list: MutableList<T>) : ObservableList<T>, AsyncCompositeList.AsyncCompositeListCallback {

    @Transient private var mListeners: ListChangeRegistry? = ListChangeRegistry()

    init {
        if (list is AsyncCompositeList) {
            list.callback = this
        }
    }

    override val size: Int
        get() = list.size

    override fun add(item: T) = list.add(item)

    override fun add(index: Int, item: T) = list.add(index, item)

    override fun addAll(collection: Collection<T>) = list.addAll(collection)

    override fun addAll(index: Int, collection: Collection<T>) = list.addAll(index, collection)

    override fun remove(element: T) = list.remove(element)

    override fun removeAt(index: Int) = list.removeAt(index)

    override fun removeAll(elements: Collection<T>) = list.removeAll(elements)

    override fun get(index: Int) = list[index]

    override fun set(index: Int, item: T) = list.set(index, item)

    override fun indexOf(element: T) = list.indexOf(element)

    override fun lastIndexOf(element: T) = list.lastIndexOf(element)

    override fun contains(element: T) = list.contains(element)

    override fun containsAll(elements: Collection<T>) = list.containsAll(elements)

    override fun retainAll(elements: Collection<T>) = list.retainAll(elements)

    override fun clear() = list.clear()

    override fun isEmpty() = list.isEmpty()

    override fun iterator() = list.iterator()

    override fun listIterator() = list.listIterator()

    override fun listIterator(index: Int) = list.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int) = list.subList(fromIndex, toIndex)

    override fun addOnListChangedCallback(listener: ObservableList.OnListChangedCallback<out ObservableList<T>>) {
        if (mListeners == null) {
            mListeners = ListChangeRegistry()
        }
        mListeners?.add(listener)
    }

    override fun removeOnListChangedCallback(listener: ObservableList.OnListChangedCallback<out ObservableList<T>>) {
        if (mListeners != null) {
            mListeners?.remove(listener)
        }
    }

    override fun notifyChanged() {
        if (mListeners != null) {
            mListeners?.notifyChanged(this)
        }
    }

}