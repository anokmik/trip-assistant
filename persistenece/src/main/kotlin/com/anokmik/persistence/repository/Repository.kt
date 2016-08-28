package com.anokmik.persistence.repository

import com.anokmik.persistence.util.AsyncCompositeList
import com.raizlabs.android.dbflow.list.FlowCursorList.OnCursorRefreshListener
import com.raizlabs.android.dbflow.list.FlowQueryList
import com.raizlabs.android.dbflow.sql.language.Condition
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable
import com.raizlabs.android.dbflow.structure.BaseModel
import com.raizlabs.android.dbflow.structure.Model
import java.lang.ref.WeakReference

abstract class Repository<T : BaseModel>(protected val cls: Class<T>) {

    fun getAll(): MutableList<T?> {
        return getFlowQueryList()
    }

    fun setAll(vararg conditions: Condition) {
        SQLite.update(cls).set(*conditions).execute()
    }

    fun getList(vararg conditions: Condition): MutableList<T> {
        return getFlowQueryList(*conditions)
    }

    fun getAsyncList(vararg conditions: Condition): MutableList<T> {
        return AsyncFlowQueryCompositeList(getFlowQueryList(*conditions))
    }

    fun get(vararg conditions: Condition): T? {
        return SQLite.select().from(cls).where(*conditions).querySingle()
    }

    fun delete(vararg conditions: Condition) {
        SQLite.delete().from(cls).where(*conditions)
    }

    private fun getFlowQueryList(vararg conditions: Condition): FlowQueryList<T> {
        return FlowQueryList.Builder(cls).modelQueriable(getModelQueriable(*conditions)).cacheModels(true).build()
    }

    private fun getModelQueriable(vararg conditions: Condition): ModelQueriable<T> {
        val from = SQLite.select().from(cls)
        return if (conditions.isEmpty()) from else from.where(*conditions)
    }

    private class AsyncFlowQueryCompositeList<T : Model>(flowQueryList: FlowQueryList<T>) : AsyncCompositeList<T>(flowQueryList) {

        private val weakReference = WeakReference(OnCursorRefreshListener<T> {
            callback?.notifyChanged()
        })

        init {
            flowQueryList.addOnCursorRefreshListener(weakReference.get())
        }

    }

}