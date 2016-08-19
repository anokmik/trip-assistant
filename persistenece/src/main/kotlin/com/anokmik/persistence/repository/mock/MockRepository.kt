package com.anokmik.persistence.repository.mock

import com.anokmik.persistence.TripAssistantDatabase
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.Model
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction

class MockRepository<in T : Model>(private val type: Class<T>) {

    fun storeModelsFast(items: List<T>) {
        FlowManager.getDatabase(TripAssistantDatabase::class.java).executeTransaction(
                FastStoreModelTransaction.saveBuilder(FlowManager.getModelAdapter(type)).addAll(items).build())
    }

    fun count() = SQLite.select().from(type).count()

}