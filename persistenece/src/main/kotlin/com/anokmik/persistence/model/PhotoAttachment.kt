package com.anokmik.persistence.model

import com.anokmik.persistence.TripAssistantDatabase
import com.raizlabs.android.dbflow.annotation.*
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = TripAssistantDatabase::class)
class PhotoAttachment : BaseModel() {

    @JvmField
    @PrimaryKey(autoincrement = true)
    var id: Long = 0

    @JvmField
    @ForeignKey(references = arrayOf(
            ForeignKeyReference(
                    columnType = Long::class,
                    columnName = "tripEvent",
                    foreignKeyColumnName = "id"
            )
    ))
    @Column
    var tripEvent: TripEvent? = null

    @JvmField
    @Column
    var description: String? = null

    @JvmField
    @Column
    var path: String? = null

}