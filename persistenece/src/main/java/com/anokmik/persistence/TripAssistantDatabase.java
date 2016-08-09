package com.anokmik.persistence;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(
        name = TripAssistantDatabase.NAME,
        version = TripAssistantDatabase.VERSION
)
public final class TripAssistantDatabase {

    public static final String NAME = "TripAssistantDatabase";

    public static final int VERSION = 1;

}