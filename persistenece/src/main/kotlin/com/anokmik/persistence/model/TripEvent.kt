package com.anokmik.persistence.model

import android.support.annotation.IntDef
import com.anokmik.persistence.TripAssistantDatabase
import com.raizlabs.android.dbflow.annotation.*
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = TripAssistantDatabase::class)
class TripEvent : BaseModel() {

    @JvmField
    @PrimaryKey(autoincrement = true)
    var id: Long = 0

    @JvmField
    @ForeignKey(references = arrayOf(
            ForeignKeyReference(
                    columnType = Long::class,
                    columnName = "trip",
                    foreignKeyColumnName = "id"
            )
    ))
    @Column
    var trip: Trip? = null;

    @ForeignKey(references = arrayOf(
            ForeignKeyReference(
                    columnType = Long::class,
                    columnName = "user",
                    foreignKeyColumnName = "id"
            )
    ))
    @Column
    var user: User? = null;

    @JvmField
    @Column
    var name: String? = null

    @JvmField
    @Column
    var comment: String? = null

    @JvmField
    @Column
    var startDate: Long? = 0

    @JvmField
    @Column
    var finishDate: Long? = 0


}