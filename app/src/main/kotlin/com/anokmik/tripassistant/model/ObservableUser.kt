package com.anokmik.tripassistant.model

import android.databinding.BaseObservable

import com.anokmik.persistence.model.User

class ObservableUser(private var user: User?) : BaseObservable() {

    fun get(): User? {
        return user
    }

    fun set(user: User?) {
        this.user = user
        notifyChange()
    }

    var firstName: String?
        get() = user?.firstName
        set(firstName) {
            user?.firstName = firstName
        }

    var lastName: String?
        get() = user?.lastName
        set(lastName) {
            user?.lastName = lastName
        }

    var isActive: Boolean?
        get() = user?.isActive
        set(isActive) {
            user?.isActive = isActive
        }

    fun save() = user?.save()

    fun delete() = user?.delete()

}