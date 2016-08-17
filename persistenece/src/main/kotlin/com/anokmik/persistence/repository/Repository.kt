package com.anokmik.persistence.repository

import com.raizlabs.android.dbflow.list.FlowQueryList
import com.raizlabs.android.dbflow.sql.language.Condition
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.BaseModel

abstract class Repository<T : BaseModel>(protected val cls: Class<T>) {

    fun getAll(): List<T?> {
        return FlowQueryList.Builder(cls).modelQueriable(SQLite.select().from(cls)).cacheModels(true).build()
    }

    fun getList(vararg conditions: Condition): List<T> {
        return FlowQueryList.Builder(cls)
                .modelQueriable(SQLite.select().from(cls).where(*conditions))
                .cacheModels(true)
                .build();
    }

    fun get(vararg conditions: Condition): T? {
        return SQLite.select().from(cls).where(*conditions).querySingle()
    }

    fun setAll(vararg conditions: Condition) {
        SQLite.update(cls).set(*conditions).execute()
    }

    fun delete(vararg conditions: Condition) {
        SQLite.delete().from(cls).where(*conditions);
    }

}