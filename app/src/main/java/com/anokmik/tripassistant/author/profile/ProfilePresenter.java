package com.anokmik.tripassistant.author.profile;

import android.databinding.ObservableBoolean;

import com.anokmik.persistence.model.Author;
import com.anokmik.persistence.repository.AuthorRepository;
import com.anokmik.tripassistant.validator.AuthorTextLengthValidator;

public final class ProfilePresenter implements ProfileContract.Presenter {

    public final ObservableBoolean firstNameValid;
    public final ObservableBoolean lastNameValid;
    public final ObservableBoolean isEditing;
    private final AuthorRepository authorRepository;
    private final Author author;
    private final ProfileContract.View view;
    private final AuthorTextLengthValidator validator;

    public ProfilePresenter(ProfileContract.View view, long authorId) {
        this.lastNameValid = new ObservableBoolean(true);
        this.firstNameValid = new ObservableBoolean(true);
        this.isEditing = new ObservableBoolean(false);
        this.authorRepository = new AuthorRepository();
        this.author = authorRepository.get(authorId);
        this.view = view;
        this.validator = new AuthorTextLengthValidator(firstNameValid, lastNameValid);

        init();
    }

    @Override
    public void save() {
        if (validator.validFields(author.firstName, author.lastName)) {
            author.save();
            view.back();
        }
    }

    @Override
    public void cancel() {
        view.setProfileTitle();
        isEditing.set(false);
    }

    @Override
    public void edit() {
        view.setEditAuthorTitle();
        isEditing.set(true);
    }

    @Override
    public void delete() {
        author.delete();
        view.back();
    }

    @Override
    public void signOut() {
        author.isActive = false;
        author.save();
        view.back();
    }

    public String getAuthorFirstName() {
        return author.firstName;
    }

    public void setAuthorFirstName(String firstName) {
        author.firstName = firstName;
    }

    public String getAuthorLastName() {
        return author.lastName;
    }

    public void setAuthorLastName(String lastName) {
        author.lastName = lastName;
    }

    private void init() {
        view.setProfileTitle();
    }

}