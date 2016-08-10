package com.anokmik.persistence.model

import android.support.annotation.IntDef
import com.anokmik.persistence.TripAssistantDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = TripAssistantDatabase::class)
class TripEvent : BaseModel() {

    @PrimaryKey(autoincrement = true)
    var id: Long = 0

    @Column
    var tripId: Long = 0

    @Column
    var authorId: Long = 0

    @Column
    var name: String? = null

    @Column
    var comment: String? = null

    @Type
    @Column
    var type: Int = 0

    @Column
    var startDate: Long = 0

    @Column
    var finishDate: Long = 0

    @IntDef(DEFAULT, TICKET, HOTEL, MEETING, PLACE_OF_INTEREST)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Type

    companion object {

        const val DEFAULT = 0L
        const val TICKET = 1L
        const val HOTEL = 2L
        const val MEETING = 3L
        const val PLACE_OF_INTEREST = 4L

    }

}