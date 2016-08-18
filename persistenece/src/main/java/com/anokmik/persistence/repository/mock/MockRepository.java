package com.anokmik.persistence.repository.mock;

import com.anokmik.persistence.TripAssistantDatabase;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import java.util.List;

public class MockRepository<T extends Model> {

    private final Class<T> type;

    public MockRepository(Class<T> type) {
        this.type = type;
    }

    public void storeModelsFast(List<T> items) {
        FlowManager.getDatabase(TripAssistantDatabase.class).executeTransaction(
                FastStoreModelTransaction.saveBuilder(FlowManager.getModelAdapter(type)).addAll(items).build());
    }

    public long count() {
        return SQLite.select().from(type).count();
    }

}