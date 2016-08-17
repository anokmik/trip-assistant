package com.anokmik.persistence.repository.mock;

import com.anokmik.persistence.TripAssistantDatabase;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import java.util.List;

public class MockRepository<T extends Model> {

    public void storeModelsFast(List<T> items, Class<T> type) {
        FlowManager.getDatabase(TripAssistantDatabase.class).executeTransaction(
                FastStoreModelTransaction.saveBuilder(FlowManager.getModelAdapter(type)).addAll(items).build());
    }

    public long count(Class<T> type) {
        return SQLite.select().from(type).count();
    }

}