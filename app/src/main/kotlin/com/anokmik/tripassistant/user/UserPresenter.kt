package com.anokmik.tripassistant.user

import com.anokmik.persistence.repository.UserRepository

class UserPresenter(private val view: UserContract.View) : UserContract.Presenter {

    private val userRepository = UserRepository()

    override fun validateUser() {
        val user = userRepository.getActive()
        if (user != null) view.showProfile(user) else view.showLogin()
    }

}