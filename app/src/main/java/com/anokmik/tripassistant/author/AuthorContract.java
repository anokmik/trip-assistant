package com.anokmik.tripassistant.author;

import com.anokmik.persistence.model.Author;

public interface AuthorContract {

    interface View {

        void showLogin();

        void showProfile(Author author);

    }

    interface Presenter {

        void validateAuthor();

    }

}