package com.anokmik.tripassistant.user.profile

import android.databinding.ObservableBoolean
import com.anokmik.persistence.repository.UserRepository
import com.anokmik.tripassistant.validator.UserTextLengthValidator

class ProfilePresenter(private val view: ProfileContract.View, val userId: Long) : ProfileContract.Presenter {

    val firstNameValid = ObservableBoolean(true)
    val lastNameValid = ObservableBoolean(true)
    val isEditing = ObservableBoolean(false)

    private val userRepository = UserRepository()
    private val validator = UserTextLengthValidator(firstNameValid, lastNameValid)
    private val user = userRepository.get(userId)

    var userFirstName: String?
        get() = user?.firstName
        set(firstName) {
            user?.firstName = firstName
        }

    var userLastName: String?
        get() = user?.lastName
        set(lastName) {
            user?.lastName = lastName
        }

    init {
        view.setProfileTitle()
    }

    override fun save() {
        if (validator.validFields(user?.firstName, user?.lastName)) {
            user?.save()
            view.back()
        }
    }

    override fun cancel() {
        view.setProfileTitle()
        isEditing.set(false)
    }

    override fun edit() {
        view.setEditUserTitle()
        isEditing.set(true)
    }

    override fun delete() {
        user?.delete()
        view.back()
    }

    override fun signOut() {
        user?.isActive = false
        user?.save()
        view.back()
    }

}