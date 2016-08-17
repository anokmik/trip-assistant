package com.anokmik.persistence.repository;

import com.raizlabs.android.dbflow.list.FlowQueryList;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

abstract class Repository<T extends BaseModel> {

    private final Class<T> cls = getType();

    public List<T> getAll() {
        return new FlowQueryList.Builder<>(cls)
                .modelQueriable(SQLite.select().from(cls))
                .cacheModels(true)
                .build();
    }

    public List<T> getList(Condition... conditions) {
        return new FlowQueryList.Builder<>(cls)
                .modelQueriable(SQLite.select().from(cls).where(conditions))
                .cacheModels(true)
                .build();
    }

    public T get(Condition... conditions) {
        return SQLite.select().from(cls).where(conditions).querySingle();
    }

    public void setAll(Condition... conditions) {
        SQLite.update(getType()).set(conditions).execute();
    }

    public void delete(Condition... conditions) {
        SQLite.delete().from(cls).where(conditions);
    }

    abstract protected Class<T> getType();

}