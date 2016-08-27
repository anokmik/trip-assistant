package com.anokmik.tripassistant.user.profile;

public interface ProfileContract {

    interface View {

        void back();

    }

    interface Presenter {

        boolean validFields();

        void save();

        void cancel();

        void edit();

        void delete();

        void signOut();

    }

}