package com.anokmik.persistence.repository.mock;

import com.anokmik.persistence.TripAssistantDatabase;
import com.anokmik.persistence.model.Trip;
import com.anokmik.persistence.repository.TripRepository;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import java.util.List;

public class MockTripRepository extends TripRepository {

    public void storeModelsFast(List<Trip> trips) {
        FlowManager.getDatabase(TripAssistantDatabase.class).executeTransaction(
                FastStoreModelTransaction.insertBuilder(FlowManager.getModelAdapter(getType())).addAll(trips).build());
    }

    public long count() {
        return SQLite.select().from(getType()).count();
    }

}