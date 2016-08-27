package com.anokmik.tripassistant.user.profile;

import com.anokmik.tripassistant.util.contract.presenter.PresenterEditable;
import com.anokmik.tripassistant.util.contract.presenter.PresenterValidator;
import com.anokmik.tripassistant.util.contract.view.ViewBack;

public interface ProfileContract {

    interface View extends ViewBack {

    }

    interface Presenter extends PresenterValidator, PresenterEditable{

        void signOut();

    }

}