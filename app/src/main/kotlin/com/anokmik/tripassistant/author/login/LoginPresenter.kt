package com.anokmik.tripassistant.author.login

import android.databinding.ObservableBoolean
import com.anokmik.persistence.repository.AuthorRepository
import com.anokmik.tripassistant.validator.AuthorTextLengthValidator

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    val firstNameValid = ObservableBoolean(true)
    val lastNameValid = ObservableBoolean(true)

    private val authorRepository = AuthorRepository()
    private val validator = AuthorTextLengthValidator(firstNameValid, lastNameValid)

    var firstName = ""
    var lastName = ""

    override fun login() {
        if (validator.validFields(firstName, lastName)) {
            val author = authorRepository.get(firstName, lastName)
            if (author == null) {
                authorRepository.setAllActive(false)
                authorRepository.add(firstName, lastName, true)
            } else if (!author.isActive) {
                authorRepository.setAllActive(false)
                author.isActive = true
                author.save()
            }
            view.back()
        }
    }

}