package com.anokmik.tripassistant.author;

public interface AuthorContract {

    interface View {

        void showLogin();

        void showProfile(long authorId);

    }

    interface Presenter {

        void validateAuthor();

    }

}