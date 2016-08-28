package com.anokmik.tripassistant.user.profile

import com.anokmik.tripassistant.util.contract.presenter.PresenterEditable
import com.anokmik.tripassistant.util.contract.presenter.PresenterValidator
import com.anokmik.tripassistant.util.contract.view.ViewBack

interface ProfileContract {

    interface View : ViewBack

    interface Presenter : PresenterValidator, PresenterEditable {

        fun signOut()

    }

}