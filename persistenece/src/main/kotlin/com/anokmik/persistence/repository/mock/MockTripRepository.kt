package com.anokmik.persistence.repository.mock

import com.anokmik.persistence.TripAssistantDatabase
import com.anokmik.persistence.model.Trip
import com.anokmik.persistence.repository.TripRepository
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction

class MockTripRepository : TripRepository() {

    fun storeModelsFast(trips: List<Trip>) {
        FlowManager.getDatabase(TripAssistantDatabase::class.java).executeTransaction(
                FastStoreModelTransaction.insertBuilder(FlowManager.getModelAdapter(cls)).addAll(trips).build())
    }

    fun count(): Long {
        return SQLite.select().from(cls).count()
    }

}