package com.anokmik.tripassistant.author.profile;

public interface ProfileContract {

    interface View {

        void setProfileTitle();

        void setEditAuthorTitle();

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