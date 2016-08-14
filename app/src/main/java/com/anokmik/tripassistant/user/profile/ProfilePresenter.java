package com.anokmik.tripassistant.user.profile;

import android.databinding.ObservableBoolean;

import com.anokmik.persistence.model.User;
import com.anokmik.persistence.repository.UserRepository;
import com.anokmik.tripassistant.validator.UserTextLengthValidator;

public final class ProfilePresenter implements ProfileContract.Presenter {

    public final ObservableBoolean firstNameValid;
    public final ObservableBoolean lastNameValid;
    public final ObservableBoolean isEditing;
    private final UserRepository userRepository;
    private final User user;
    private final ProfileContract.View view;
    private final UserTextLengthValidator validator;

    public ProfilePresenter(ProfileContract.View view, long userId) {
        this.lastNameValid = new ObservableBoolean(true);
        this.firstNameValid = new ObservableBoolean(true);
        this.isEditing = new ObservableBoolean(false);
        this.userRepository = new UserRepository();
        this.user = userRepository.get(userId);
        this.view = view;
        this.validator = new UserTextLengthValidator(firstNameValid, lastNameValid);

        init();
    }

    @Override
    public void save() {
        if (validator.validFields(user.firstName, user.lastName)) {
            user.save();
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
        view.setEditUserTitle();
        isEditing.set(true);
    }

    @Override
    public void delete() {
        user.delete();
        view.back();
    }

    @Override
    public void signOut() {
        user.isActive = false;
        user.save();
        view.back();
    }

    public String getUserFirstName() {
        return user.firstName;
    }

    public void setUserFirstName(String firstName) {
        user.firstName = firstName;
    }

    public String getUserLastName() {
        return user.lastName;
    }

    public void setUserLastName(String lastName) {
        user.lastName = lastName;
    }

    private void init() {
        view.setProfileTitle();
    }

}