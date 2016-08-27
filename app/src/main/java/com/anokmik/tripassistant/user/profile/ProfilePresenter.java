package com.anokmik.tripassistant.user.profile;

import android.databinding.ObservableBoolean;

import com.anokmik.persistence.model.User;
import com.anokmik.persistence.repository.UserRepository;
import com.anokmik.tripassistant.model.ObservableUser;
import com.anokmik.tripassistant.validator.TextLengthValidator;
import com.anokmik.tripassistant.validator.Threshold;

public final class ProfilePresenter implements ProfileContract.Presenter {

    public final ObservableBoolean firstNameValid;
    public final ObservableBoolean lastNameValid;
    public final ObservableBoolean isEditing;
    public final ObservableUser observableUser;

    private final UserRepository userRepository;
    private final TextLengthValidator validator;
    private final ProfileContract.View view;
    private final long userId;

    public ProfilePresenter(ProfileContract.View view, long userId) {
        this.lastNameValid = new ObservableBoolean(true);
        this.firstNameValid = new ObservableBoolean(true);
        this.isEditing = new ObservableBoolean(false);

        this.userRepository = new UserRepository();
        this.validator = new TextLengthValidator();
        this.view = view;
        this.userId = userId;

        this.observableUser = new ObservableUser(getUser());
    }

    @Override
    public boolean validFields() {
        firstNameValid.set(validator.higherThan(observableUser.getFirstName(), Threshold.NAME_LENGTH));
        lastNameValid.set(validator.higherThan(observableUser.getLastName(), Threshold.NAME_LENGTH));
        return firstNameValid.get() && lastNameValid.get();
    }

    @Override
    public void save() {
        if (validFields()) {
            observableUser.save();
            view.back();
        }
    }

    @Override
    public void cancel() {
        isEditing.set(false);
        observableUser.set(getUser());
    }

    @Override
    public void edit() {
        isEditing.set(true);
    }

    @Override
    public void delete() {
        observableUser.delete();
        view.back();
    }

    @Override
    public void signOut() {
        observableUser.setActive(false);
        observableUser.save();
        view.back();
    }

    private User getUser() {
        return userRepository.get(userId);
    }

}