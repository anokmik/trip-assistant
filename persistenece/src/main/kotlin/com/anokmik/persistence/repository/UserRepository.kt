package com.anokmik.persistence.repository

import com.anokmik.persistence.model.User
import com.anokmik.persistence.model.User_Table
import com.raizlabs.android.dbflow.sql.language.SQLite

open class UserRepository : Repository<User>(User::class.java) {

    fun add(firstName: String, lastName: String, isActive: Boolean) {
        SQLite.insert(cls).columns(User_Table.firstName, User_Table.lastName, User_Table.isActive).values(firstName, lastName, isActive).execute()
    }

    fun get(id: Long): User? {
        return get(User_Table.id.eq(id))
    }

    fun get(firstName: String?, lastName: String?): User? {
        return get(User_Table.firstName.eq(firstName), User_Table.lastName.eq(lastName))
    }

    fun getActive(): User? {
        return get(User_Table.isActive.`is`(true))
    }

    fun setAllActive(isActive: Boolean) {
        setAll(User_Table.isActive.`is`(isActive))
    }

}