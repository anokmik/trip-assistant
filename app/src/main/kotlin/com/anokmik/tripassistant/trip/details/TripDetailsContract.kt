package com.anokmik.tripassistant.trip.details

import com.anokmik.persistence.model.TripEvent
import com.anokmik.tripassistant.databinding.adapter.OnItemClickListener
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter
import com.anokmik.tripassistant.dialog.DateHandler
import com.anokmik.tripassistant.util.contract.presenter.PresenterEditable
import com.anokmik.tripassistant.util.contract.presenter.PresenterStartFinishDates
import com.anokmik.tripassistant.util.contract.presenter.PresenterValidator
import com.anokmik.tripassistant.util.contract.view.ViewBack
import com.anokmik.tripassistant.util.contract.view.ViewControls
import com.anokmik.tripassistant.util.contract.view.ViewListItem
import com.anokmik.tripassistant.util.contract.view.ViewStartFinishDates

interface TripDetailsContract {

    interface Presenter : TripEventListener, PresenterStartFinishDates, PresenterValidator, PresenterEditable, OnItemClickListener<TripEvent> {

        val tripEvents: MutableList<TripEvent>

        val viewHolderPresenter: ViewHolderPresenter<TripEvent>

    }

    interface View : ViewListItem, ViewStartFinishDates, ViewControls, ViewBack, DateHandler {

        fun addTripEvent()

        fun viewTripEvent(tripEventId: Long)

    }

}