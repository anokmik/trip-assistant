package com.anokmik.tripassistant.author

import com.anokmik.persistence.model.Author

interface AuthorContract {

    interface View {

        fun showLogin()

        fun showProfile(author: Author)

    }

    interface Presenter {

        fun validateAuthor()

    }

}