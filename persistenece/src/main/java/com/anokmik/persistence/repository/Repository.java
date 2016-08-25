package com.anokmik.persistence.repository;

import com.anokmik.persistence.util.AsyncCompositeList;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.list.FlowCursorList.OnCursorRefreshListener;
import com.raizlabs.android.dbflow.list.FlowQueryList;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.Model;

import java.lang.ref.WeakReference;
import java.util.List;

abstract class Repository<T extends BaseModel> {

    private final Class<T> cls = getType();

    public List<T> getAll() {
        return getFlowQueryList(null);
    }

    public void setAll(Condition... conditions) {
        SQLite.update(getType()).set(conditions).execute();
    }

    public List<T> getList(Condition... conditions) {
        return getFlowQueryList(conditions);
    }

    public List<T> getAsyncList(Condition... conditions) {
        return new AsyncFlowQueryCompositeList<>(getFlowQueryList(conditions));
    }

    public T get(Condition... conditions) {
        return SQLite.select().from(cls).where(conditions).querySingle();
    }

    public void delete(Condition... conditions) {
        SQLite.delete().from(cls).where(conditions);
    }

    private FlowQueryList<T> getFlowQueryList(Condition... conditions) {
        return new FlowQueryList.Builder<>(cls)
                .modelQueriable(getModelQueriable(conditions))
                .cacheModels(true)
                .build();
    }

    private ModelQueriable<T> getModelQueriable(Condition... conditions) {
        From<T> from = SQLite.select().from(cls);
        return (conditions != null) ? from.where(conditions) : from;
    }

    abstract Class<T> getType();

    private static class AsyncFlowQueryCompositeList<T extends Model> extends AsyncCompositeList<T> {

        final WeakReference<OnCursorRefreshListener<T>> weakReference = new WeakReference<OnCursorRefreshListener<T>>(new OnCursorRefreshListener<T>() {

            @Override
            public void onCursorRefreshed(FlowCursorList<T> cursorList) {
                if (callback != null) {
                    callback.notifyChanged();
                }
            }

        });

        public AsyncFlowQueryCompositeList(FlowQueryList<T> flowQueryList) {
            super(flowQueryList);
            flowQueryList.addOnCursorRefreshListener(weakReference.get());
        }

    }

}