package com.anokmik.persistence.model

import com.anokmik.persistence.TripAssistantDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = TripAssistantDatabase::class)
class User : BaseModel() {

    @JvmField
    @PrimaryKey(autoincrement = true)
    var id: Long = 0

    @JvmField
    @Column
    var firstName: String? = null

    @JvmField
    @Column
    var lastName: String? = null

    @JvmField
    @Column
    var isActive: Boolean = false

}