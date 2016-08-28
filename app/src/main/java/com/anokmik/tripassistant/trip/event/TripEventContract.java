package com.anokmik.tripassistant.trip.event;

import com.anokmik.persistence.model.PhotoAttachment;
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter;
import com.anokmik.tripassistant.dialog.DateHandler;
import com.anokmik.tripassistant.util.contract.presenter.PresenterEditable;
import com.anokmik.tripassistant.util.contract.presenter.PresenterStartFinishDates;
import com.anokmik.tripassistant.util.contract.presenter.PresenterValidator;
import com.anokmik.tripassistant.util.contract.view.ViewBack;
import com.anokmik.tripassistant.util.contract.view.ViewControls;
import com.anokmik.tripassistant.util.contract.view.ViewListItem;
import com.anokmik.tripassistant.util.contract.view.ViewStartFinishDates;

import java.util.List;

public interface TripEventContract {

    interface Presenter extends PresenterStartFinishDates, PresenterValidator, PresenterEditable, PhotoAttachmentListener {

        List<PhotoAttachment> getPhotoAttachments();

        ViewHolderPresenter<PhotoAttachment> getViewHolderPresenter();

    }

    interface View extends ViewListItem, ViewStartFinishDates, ViewControls, ViewBack, DateHandler {

        void takePhotoAttachment();

        void pickPhotoAttachment();

    }

}