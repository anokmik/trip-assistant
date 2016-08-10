package com.anokmik.tripassistant.author.login

interface LoginContract {

    interface View {

        fun back()

    }

    interface Presenter {

        fun login()

    }

}