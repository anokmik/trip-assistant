package com.anokmik.persistence.model

import com.anokmik.persistence.TripAssistantDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = TripAssistantDatabase::class)
class Author : BaseModel() {

    @PrimaryKey(autoincrement = true)
    var id: Long = 0

    @Column
    var firstName: String? = null

    @Column
    var lastName: String? = null

    @Column
    var isActive: Boolean = false

}