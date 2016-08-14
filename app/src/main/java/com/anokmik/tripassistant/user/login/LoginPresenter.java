package com.anokmik.tripassistant.user.login;

import android.databinding.ObservableBoolean;

import com.anokmik.persistence.model.User;
import com.anokmik.persistence.repository.UserRepository;
import com.anokmik.tripassistant.validator.UserTextLengthValidator;

public final class LoginPresenter implements LoginContract.Presenter {

    public final ObservableBoolean firstNameValid;
    public final ObservableBoolean lastNameValid;

    private final LoginContract.View view;
    private final UserRepository userRepository;
    private final UserTextLengthValidator validator;

    private String firstName;
    private String lastName;

    public LoginPresenter(LoginContract.View view) {
        this.firstNameValid = new ObservableBoolean(true);
        this.lastNameValid = new ObservableBoolean(true);

        this.view = view;
        this.userRepository = new UserRepository();
        this.validator = new UserTextLengthValidator(firstNameValid, lastNameValid);
    }

    @Override
    public void login() {
        if (validator.validFields(firstName, lastName)) {
            User user = userRepository.get(firstName, lastName);
            if (user == null) {
                userRepository.setAllActive(false);
                userRepository.add(firstName, lastName, true);
            } else if (!user.isActive) {
                userRepository.setAllActive(false);
                user.isActive = true;
                user.save();
            }
            view.back();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}