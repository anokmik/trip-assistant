package com.anokmik.tripassistant.trip.details;

import com.anokmik.persistence.model.TripEvent;
import com.anokmik.tripassistant.databinding.adapter.OnItemClickListener;
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

public interface TripDetailsContract {

    interface Presenter extends TripEventListener, PresenterStartFinishDates, PresenterValidator, PresenterEditable, OnItemClickListener<TripEvent> {

        List<TripEvent> getTripEvents();

        ViewHolderPresenter<TripEvent> getViewHolderPresenter();

    }

    interface View extends ViewListItem, ViewStartFinishDates, ViewControls, ViewBack, DateHandler {

        void addTripEvent();

        void viewTripEvent(long tripEventId);

    }

}