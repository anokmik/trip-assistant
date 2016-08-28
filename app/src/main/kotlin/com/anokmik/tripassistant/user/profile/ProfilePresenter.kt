package com.anokmik.tripassistant.user.profile

import android.databinding.ObservableBoolean
import com.anokmik.persistence.repository.UserRepository
import com.anokmik.tripassistant.model.ObservableUser
import com.anokmik.tripassistant.validator.NAME_LENGTH
import com.anokmik.tripassistant.validator.TextLengthValidator

class ProfilePresenter(private val view: ProfileContract.View, private val userId: Long) : ProfileContract.Presenter {

    val firstNameValid = ObservableBoolean(true)
    val lastNameValid = ObservableBoolean(true)
    val isEditing = ObservableBoolean(false)

    private val userRepository = UserRepository()
    private val validator = TextLengthValidator()

    val observableUser = ObservableUser(getUser())

    override fun validFields(): Boolean {
        firstNameValid.set(validator.higherThan(observableUser.firstName, NAME_LENGTH))
        lastNameValid.set(validator.higherThan(observableUser.lastName, NAME_LENGTH))
        return firstNameValid.get() && lastNameValid.get()
    }

    override fun save() {
        if (validFields()) {
            observableUser.save()
            view.back()
        }
    }

    override fun cancel() {
        isEditing.set(false)
        observableUser.set(getUser())
    }

    override fun edit() = isEditing.set(true)

    override fun delete() {
        observableUser.delete()
        view.back()
    }

    override fun signOut() {
        observableUser.isActive = false
        observableUser.save()
        view.back()
    }

    private fun getUser() = userRepository.get(userId)

}