package com.anokmik.tripassistant.user;

import com.anokmik.persistence.model.User;

public interface UserContract {

    interface View {

        void showLogin();

        void showProfile(User user);

    }

    interface Presenter {

        void validateUser();

    }

}