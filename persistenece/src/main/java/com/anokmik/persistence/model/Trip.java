package com.anokmik.persistence.model;

import com.anokmik.persistence.TripAssistantDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = TripAssistantDatabase.class)
public final class Trip extends BaseModel {

    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String title;

    @Column
    public String description;

    @Column
    public long startDate;

    @Column
    public long finishDate;

}