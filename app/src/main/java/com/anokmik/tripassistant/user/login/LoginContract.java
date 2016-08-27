package com.anokmik.tripassistant.user.login;

public interface LoginContract {

    interface View {

        void back();

    }

    interface Presenter {

        boolean validFields();

        void login();

    }

}