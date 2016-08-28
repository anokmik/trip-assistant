package com.anokmik.tripassistant.user.login

import android.databinding.ObservableBoolean
import com.anokmik.persistence.repository.UserRepository
import com.anokmik.tripassistant.validator.NAME_LENGTH
import com.anokmik.tripassistant.validator.TextLengthValidator

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    val firstNameValid = ObservableBoolean(true)
    val lastNameValid = ObservableBoolean(true)

    private val userRepository = UserRepository()
    private val validator = TextLengthValidator()

    var firstName = ""
    var lastName = ""

    override fun validFields(): Boolean {
        firstNameValid.set(validator.higherThan(firstName, NAME_LENGTH))
        lastNameValid.set(validator.higherThan(lastName, NAME_LENGTH))
        return firstNameValid.get() && lastNameValid.get()
    }

    override fun login() {
        if (validFields()) {
            val user = userRepository.get(firstName, lastName)
            if (user == null) {
                userRepository.setAllActive(false)
                userRepository.add(firstName, lastName, true)
            } else if (user.isActive ?: true) {
                userRepository.setAllActive(false)
                user.isActive = true
                user.save()
            }
            view.back()
        }
    }

}