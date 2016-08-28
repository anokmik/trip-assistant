package com.anokmik.persistence.util

abstract class AsyncCompositeList<T> protected constructor(protected val list: MutableList<T>) : MutableList<T> {

    var callback: AsyncCompositeListCallback? = null

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

    override operator fun set(index: Int, item: T) = list.set(index, item)

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

    interface AsyncCompositeListCallback {

        fun notifyChanged()

    }

}