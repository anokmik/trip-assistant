package com.anokmik.tripassistant.user.login

import com.anokmik.tripassistant.util.contract.presenter.PresenterValidator
import com.anokmik.tripassistant.util.contract.view.ViewBack

interface LoginContract {

    interface View : ViewBack

    interface Presenter : PresenterValidator {

        fun login()

    }

}