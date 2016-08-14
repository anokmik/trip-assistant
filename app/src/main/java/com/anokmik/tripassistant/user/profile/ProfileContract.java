package com.anokmik.tripassistant.user.profile;

public interface ProfileContract {

    interface View {

        void setProfileTitle();

        void setEditUserTitle();

        void back();

    }

    interface Presenter {

        void save();

        void cancel();

        void edit();

        void delete();

        void signOut();

    }

}