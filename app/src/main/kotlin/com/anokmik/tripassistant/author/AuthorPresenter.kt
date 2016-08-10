package com.anokmik.tripassistant.author

import com.anokmik.persistence.repository.AuthorRepository

class AuthorPresenter(private val view: AuthorContract.View) : AuthorContract.Presenter {

    private val authorRepository = AuthorRepository()

    override fun validateAuthor() {
        val author = authorRepository.getActive()
        if (author != null) view.showProfile(author) else view.showLogin()
    }

}