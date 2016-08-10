package com.anokmik.persistence.model

import com.anokmik.persistence.TripAssistantDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = TripAssistantDatabase::class)
class PhotoAttachment : BaseModel() {

    @PrimaryKey(autoincrement = true)
    var id: Long = 0

    @Column
    var tripEventId: Long = 0

    @Column
    var description: String? = null

    @Column
    var path: String? = null

}