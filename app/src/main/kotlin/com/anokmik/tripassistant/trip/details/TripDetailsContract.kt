package com.anokmik.tripassistant.trip.details

import com.anokmik.persistence.model.Trip
import com.anokmik.persistence.model.TripEvent
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter

interface TripDetailsContract {

    interface Presenter {

        val trip: Trip?

        val tripEvents: List<TripEvent>

        val viewHolderPresenter: ViewHolderPresenter<TripEvent>

        fun showStartDatePicker()

        fun showFinishDatePicker()

        fun setStartDate(startDate: Long)

        fun setFinishDate(finishDate: Long)

        fun save()

        fun edit()

        fun delete()

    }

    interface TripEventListener {

        fun addEvent()

        fun deleteEvent(tripEvent: TripEvent)

    }

    interface View {

        val rowItemLayoutId: Int

        val itemBindingId: Int

        val itemListenerBindingId: Int

        val itemIsEditingBindingId: Int

        fun showStartDatePickerDialog(startDate: Long)

        fun showFinishDatePickerDialog(finishDate: Long)

        fun addTripEvent()

        fun viewTripEvent(tripEventId: Long)

        fun enableSaveMode()

        fun enableEditMode()

        fun back()

    }


}