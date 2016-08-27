package com.anokmik.tripassistant.user.login;

import com.anokmik.tripassistant.util.contract.presenter.PresenterValidator;
import com.anokmik.tripassistant.util.contract.view.ViewBack;

public interface LoginContract {

    interface View extends ViewBack {

    }

    interface Presenter extends PresenterValidator {

        void login();

    }

}