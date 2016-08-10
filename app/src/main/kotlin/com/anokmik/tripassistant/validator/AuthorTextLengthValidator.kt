package com.anokmik.tripassistant.validator

import android.databinding.ObservableBoolean

class AuthorTextLengthValidator(private val firstNameValid: ObservableBoolean, private val lastNameValid: ObservableBoolean) : TextLengthValidator() {

    private val minTextLength = 5

    fun validFields(firstName: String?, lastName: String?): Boolean {
        firstNameValid.set(higherThan(firstName, minTextLength))
        lastNameValid.set(higherThan(lastName, minTextLength))
        return firstNameValid.get() && lastNameValid.get()
    }

}