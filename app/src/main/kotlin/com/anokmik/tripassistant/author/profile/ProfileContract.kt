package com.anokmik.tripassistant.author.profile

interface ProfileContract {

    interface View {

        fun setProfileTitle()

        fun setEditAuthorTitle()

        fun back()

    }

    interface Presenter {

        fun save()

        fun cancel()

        fun edit()

        fun delete()

        fun signOut()

    }

}