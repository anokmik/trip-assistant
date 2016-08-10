package com.anokmik.persistence.repository

import com.anokmik.persistence.model.Author
import com.anokmik.persistence.model.Author_Table
import com.raizlabs.android.dbflow.sql.language.SQLite

open class AuthorRepository : Repository<Author>(Author::class.java) {

    fun add(firstName: String, lastName: String, isActive: Boolean) {
        SQLite.insert(cls).columns(Author_Table.firstName, Author_Table.lastName, Author_Table.isActive).values(firstName, lastName, isActive).execute()
    }

    fun get(id: Long): Author? {
        return get(Author_Table.id.eq(id))
    }

    fun get(firstName: String?, lastName: String?): Author? {
        return get(Author_Table.firstName.eq(firstName), Author_Table.lastName.eq(lastName))
    }

    fun getActive(): Author? {
        return get(Author_Table.isActive.`is`(true))
    }

    fun setAllActive(isActive: Boolean) {
        setAll(Author_Table.isActive.`is`(isActive))
    }

}