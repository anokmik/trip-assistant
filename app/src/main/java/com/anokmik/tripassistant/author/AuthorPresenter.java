package com.anokmik.tripassistant.author;

import com.anokmik.persistence.model.Author;
import com.anokmik.persistence.repository.AuthorRepository;

public final class AuthorPresenter implements AuthorContract.Presenter {

    private final AuthorContract.View view;
    private final AuthorRepository authorRepository;

    public AuthorPresenter(AuthorContract.View view) {
        this.view = view;
        this.authorRepository = new AuthorRepository();
    }

    @Override
    public void validateAuthor() {
        Author author = authorRepository.getActive();
        if (author != null) {
            view.showProfile(author);
        } else {
            view.showLogin();
        }
    }

}