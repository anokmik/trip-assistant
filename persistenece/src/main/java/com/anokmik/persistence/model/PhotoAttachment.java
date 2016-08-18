package com.anokmik.persistence.model;

import com.anokmik.persistence.TripAssistantDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = TripAssistantDatabase.class)
public final class PhotoAttachment extends BaseModel {

    @PrimaryKey(autoincrement = true)
    public long id;

    @ForeignKey(references = {
            @ForeignKeyReference(
                    columnType = long.class,
                    columnName = "tripEvent",
                    foreignKeyColumnName = "id"
            )
    })
    @Column
    public TripEvent tripEvent;

    @Column
    public String path;

}