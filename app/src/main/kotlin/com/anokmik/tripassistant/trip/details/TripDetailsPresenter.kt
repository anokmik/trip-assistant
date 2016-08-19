package com.anokmik.tripassistant.trip.details

import android.databinding.ObservableBoolean
import com.anokmik.persistence.model.TripEvent
import com.anokmik.persistence.model.TripEvent_Table
import com.anokmik.persistence.model.Trip_Table
import com.anokmik.persistence.repository.TripEventRepository
import com.anokmik.persistence.repository.TripRepository
import com.anokmik.tripassistant.databinding.adapter.OnItemClickListener
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter

class TripDetailsPresenter(private val view: TripDetailsContract.View, private val tripId: Long) :
        TripDetailsContract.Presenter, TripDetailsContract.TripEventListener, OnItemClickListener<TripEvent> {

    private val tripRepository = TripRepository()

    private val tripEventRepository = TripEventRepository()

    val isEditing = ObservableBoolean(tripId == 0L)

    val titleValid = ObservableBoolean(tripId != 0L)

    override val trip = tripRepository.get(Trip_Table.id.`is`(tripId))

    override val tripEvents = tripEventRepository.getList(TripEvent_Table.trip.`is`(tripId))

    override val viewHolderPresenter = ViewHolderPresenter.Builder<TripEvent>(view.rowItemLayoutId, view.itemBindingId)
            .setItemClickListener(this)
            .mapVariable(view.itemListenerBindingId, this)
            .mapVariable(view.itemIsEditingBindingId, isEditing)
            .build()

    override fun showStartDatePicker() {
        trip?.let {
            view.showStartDatePickerDialog(trip.startDate)
        }
    }

    override fun showFinishDatePicker() {
        trip?.let {
            view.showFinishDatePickerDialog(trip.finishDate)
        }
    }

    override fun setStartDate(startDate: Long) {
        trip?.startDate = startDate
    }

    override fun setFinishDate(finishDate: Long) {
        trip?.finishDate = finishDate
    }

    override fun save() {
        isEditing.set(false)
        trip?.save()
        view.enableEditMode()
    }

    override fun edit() {
        isEditing.set(true)
        view.enableSaveMode()
    }

    override fun delete() {
        for (tripEvent in tripEvents) {
            tripEvent.delete()
        }
        trip?.delete();
        view.back();
    }

    override fun addEvent() {
        view.addTripEvent()
    }

    override fun deleteEvent(tripEvent: TripEvent) {
        tripEvent.delete()
    }

    override fun onItemClick(item: TripEvent?, position: Int) {
        item?.let {
            view.viewTripEvent(item.id)
        }
    }

    var tripTitle: String?
        get() = trip?.title
        set(title) {
            trip?.title = title
        }

    var tripDescription: String?
        get() = trip?.description
        set(description) {
            trip?.description = description
        }

    val tripStartDate: Long?
        get() = trip?.startDate

    val tripFinishDate: Long?
        get() = trip?.finishDate

}
