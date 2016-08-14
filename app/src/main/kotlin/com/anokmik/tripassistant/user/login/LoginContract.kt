package com.anokmik.tripassistant.user.login

interface LoginContract {

    interface View {

        fun back()

    }

    interface Presenter {

        fun login()

    }

}