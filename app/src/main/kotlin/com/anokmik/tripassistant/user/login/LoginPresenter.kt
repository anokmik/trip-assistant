package com.anokmik.tripassistant.user.login

import android.databinding.ObservableBoolean
import com.anokmik.persistence.repository.UserRepository
import com.anokmik.tripassistant.validator.UserTextLengthValidator

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    val firstNameValid = ObservableBoolean(true)
    val lastNameValid = ObservableBoolean(true)

    private val userRepository = UserRepository()
    private val validator = UserTextLengthValidator(firstNameValid, lastNameValid)

    var firstName = ""
    var lastName = ""

    override fun login() {
        if (validator.validFields(firstName, lastName)) {
            val user = userRepository.get(firstName, lastName)
            if (user == null) {
                userRepository.setAllActive(false)
                userRepository.add(firstName, lastName, true)
            } else if (!user.isActive) {
                userRepository.setAllActive(false)
                user.isActive = true
                user.save()
            }
            view.back()
        }
    }

}