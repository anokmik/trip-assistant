package com.anokmik.tripassistant.user

import com.anokmik.persistence.model.User

interface UserContract {

    interface View {

        fun showLogin()

        fun showProfile(user: User)

    }

    interface Presenter {

        fun validateUser()

    }

}