package com.anokmik.persistence.repository.mock

import com.anokmik.persistence.TripAssistantDatabase
import com.anokmik.persistence.model.Trip
import com.anokmik.persistence.repository.TripRepository
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.Model
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction

class MockRepository<T : Model> {

    fun storeModelsFast(items: List<T>, type: Class<T>) {
        FlowManager.getDatabase(TripAssistantDatabase::class.java).executeTransaction(
                FastStoreModelTransaction.saveBuilder(FlowManager.getModelAdapter(type)).addAll(items).build())
    }

    fun count(type: Class<T>): Long {
        return SQLite.select().from(type).count()
    }

}