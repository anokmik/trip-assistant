package com.anokmik.persistence.util;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class AsyncCompositeList<T> implements List<T> {

    @NonNull
    protected final List<T> list;

    protected AsyncCompositeListCallback callback;

    protected AsyncCompositeList(@NonNull List<T> list) {
        this.list = list;
    }

    @Override
    public boolean add(T item) {
        return list.add(item);
    }

    @Override
    public void add(int index, T item) {
        list.add(index, item);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return list.addAll(collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        return list.addAll(index, collection);
    }

    @Override
    public T remove(int index) {
        return list.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return list.removeAll(collection);
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public T set(int index, T item) {
        return list.set(index, item);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return list.containsAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return list.retainAll(collection);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return list.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<T> listIterator(int index) {
        return list.listIterator(index);
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @NonNull
    @Override
    public <T1> T1[] toArray(T1[] tArray) {
        return list.toArray(tArray);
    }

    @NonNull
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    public void setCallback(AsyncCompositeListCallback callback) {
        this.callback = callback;
    }

    public interface AsyncCompositeListCallback {

        void notifyChanged();

    }

}