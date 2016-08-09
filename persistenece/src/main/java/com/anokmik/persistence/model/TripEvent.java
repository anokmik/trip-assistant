package com.anokmik.persistence.model;

import android.support.annotation.IntDef;

import com.anokmik.persistence.TripAssistantDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Table(database = TripAssistantDatabase.class)
public final class TripEvent extends BaseModel {

    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public long tripId;

    @Column
    public long authorId;

    @Column
    public String name;

    @Column
    public String comment;

    @Type
    @Column
    public int type;

    @Column
    public long startDate;

    @Column
    public long finishDate;

    @IntDef({Type.DEFAULT, Type.TICKET, Type.HOTEL, Type.MEETING, Type.PLACE_OF_INTEREST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
        int DEFAULT = 0;
        int TICKET = 1;
        int HOTEL = 2;
        int MEETING = 3;
        int PLACE_OF_INTEREST = 4;
    }

}