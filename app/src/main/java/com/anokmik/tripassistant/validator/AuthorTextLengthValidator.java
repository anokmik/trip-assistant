package com.anokmik.tripassistant.validator;

import android.databinding.ObservableBoolean;

public final class AuthorTextLengthValidator extends TextLengthValidator {

    private final ObservableBoolean firstNameValid;
    private final ObservableBoolean lastNameValid;

    private final int minTextLength;

    public AuthorTextLengthValidator(ObservableBoolean firstNameValid, ObservableBoolean lastNameValid) {
        this.firstNameValid = firstNameValid;
        this.lastNameValid = lastNameValid;
        this.minTextLength = 5;
    }

    public boolean validFields(String firstName, String lastName) {
        firstNameValid.set(higherThan(firstName, minTextLength));
        lastNameValid.set(higherThan(lastName, minTextLength));
        return firstNameValid.get() && lastNameValid.get();
    }

}