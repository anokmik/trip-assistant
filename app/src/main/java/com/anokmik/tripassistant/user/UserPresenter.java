package com.anokmik.tripassistant.user;

import com.anokmik.persistence.model.User;
import com.anokmik.persistence.repository.UserRepository;

public final class UserPresenter implements UserContract.Presenter {

    private final UserContract.View view;
    private final UserRepository userRepository;

    public UserPresenter(UserContract.View view) {
        this.view = view;
        this.userRepository = new UserRepository();
    }

    @Override
    public void validateUser() {
        User user = userRepository.getActive();
        if (user != null) {
            view.showProfile(user);
        } else {
            view.showLogin();
        }
    }

}