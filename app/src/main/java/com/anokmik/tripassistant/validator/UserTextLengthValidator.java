package com.anokmik.tripassistant.validator;

import android.databinding.ObservableBoolean;

public final class UserTextLengthValidator extends TextLengthValidator {

    private final ObservableBoolean firstNameValid;
    private final ObservableBoolean lastNameValid;

    private final int minTextLength;

    public UserTextLengthValidator(ObservableBoolean firstNameValid, ObservableBoolean lastNameValid) {
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