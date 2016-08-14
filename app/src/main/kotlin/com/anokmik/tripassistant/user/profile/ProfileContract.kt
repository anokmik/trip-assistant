package com.anokmik.tripassistant.user.profile

interface ProfileContract {

    interface View {

        fun setProfileTitle()

        fun setEditUserTitle()

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