package com.anokmik.tripassistant.author.login;

import android.databinding.ObservableBoolean;

import com.anokmik.persistence.model.Author;
import com.anokmik.persistence.repository.AuthorRepository;
import com.anokmik.tripassistant.validator.AuthorTextLengthValidator;

public final class LoginPresenter implements LoginContract.Presenter {

    public final ObservableBoolean firstNameValid;
    public final ObservableBoolean lastNameValid;

    private final LoginContract.View view;
    private final AuthorRepository authorRepository;
    private final AuthorTextLengthValidator validator;

    private String firstName;
    private String lastName;

    public LoginPresenter(LoginContract.View view) {
        this.firstNameValid = new ObservableBoolean(true);
        this.lastNameValid = new ObservableBoolean(true);

        this.view = view;
        this.authorRepository = new AuthorRepository();
        this.validator = new AuthorTextLengthValidator(firstNameValid, lastNameValid);
    }

    @Override
    public void login() {
        if (validator.validFields(firstName, lastName)) {
            Author author = authorRepository.get(firstName, lastName);
            if (author == null) {
                authorRepository.setAllActive(false);
                authorRepository.add(firstName, lastName, true);
            } else if (!author.isActive) {
                authorRepository.setAllActive(false);
                author.isActive = true;
                author.save();
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