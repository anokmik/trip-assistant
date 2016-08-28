package com.anokmik.tripassistant.trip.details

import android.databinding.ObservableBoolean
import com.anokmik.persistence.model.Trip
import com.anokmik.persistence.model.TripEvent
import com.anokmik.persistence.model.Trip_Table
import com.anokmik.persistence.model.TripEvent_Table
import com.anokmik.persistence.repository.TripEventRepository
import com.anokmik.persistence.repository.TripRepository
import com.anokmik.tripassistant.databinding.ObservableCompositeList
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter
import com.anokmik.tripassistant.model.ObservableTrip
import com.anokmik.tripassistant.trip.ADD
import com.anokmik.tripassistant.trip.Mode
import com.anokmik.tripassistant.trip.VIEW
import com.anokmik.tripassistant.util.DateUtils
import com.anokmik.tripassistant.validator.TextLengthValidator

class TripDetailsPresenter(private val view: TripDetailsContract.View, @Mode val mode: Long, private val tripId: Long) : TripDetailsContract.Presenter {

    val isEditing = ObservableBoolean(isEditingInitialValue())
    val titleValid = ObservableBoolean(titleValidInitialValue())

    private val tripRepository = TripRepository()
    private val tripEventRepository = TripEventRepository()
    private val validator = TextLengthValidator()

    private val trip: Trip?
        get() {
            when (mode) {
                ADD -> {
                    val trip = Trip()
                    trip.startDate = System.currentTimeMillis()
                    trip.finishDate = System.currentTimeMillis()
                    return trip
                }
                VIEW -> return tripRepository.get(Trip_Table.id.`is`(tripId))
                else -> throw IllegalArgumentException()
            }
        }

    val observableTrip = ObservableTrip(trip)

    override val tripEvents = ObservableCompositeList(tripEventRepository.getAsyncList(TripEvent_Table.trip.`is`(tripId)))

    override val viewHolderPresenter = ViewHolderPresenter.Builder<TripEvent>(view.rowItemLayoutId, view.itemBindingId)
            .setItemClickListener(this)
            .setAdapterPositionProviderBindingId(view.adapterPositionProviderBindingId)
            .mapVariable(view.itemListenerBindingId, this)
            .mapVariable(view.itemIsEditingBindingId, isEditing)
            .build()

    override fun showStartDatePicker() = view.showStartDatePickerDialog(observableTrip.startDate)

    override fun showFinishDatePicker() = view.showFinishDatePickerDialog(observableTrip.finishDate)

    override fun setStartDate(startDate: Long) {
        trip?.startDate = startDate
    }

    override fun setFinishDate(finishDate: Long) {
        trip?.finishDate = finishDate
    }

    override fun validFields(): Boolean {
        titleValid.set(validator.notEmpty(observableTrip.title))
        val validDates = DateUtils.validDates(observableTrip.startDate, observableTrip.finishDate)
        if (!validDates) {
            view.showDatesInvalidError()
        }
        return titleValid.get() && validDates
    }

    override fun save() {
        if (validFields()) {
            isEditing.set(false)
            observableTrip.save()
            handleSave()
        }
    }

    override fun cancel() = handleCancel()

    override fun edit() {
        isEditing.set(true)
        view.enableSaveControls()
    }

    override fun delete() {
        for (tripEvent in tripEvents) {
            tripEvent.delete()
        }
        observableTrip.delete()
        view.back()
    }

    override fun addEvent() = view.addTripEvent()

    override fun deleteEvent(position: Int) {
        tripEvents.remove(tripEvents[position])
    }

    override fun onItemClick(item: TripEvent?, position: Int) {
        item?.let {
            view.viewTripEvent(item.id)
        }
    }

    private fun isEditingInitialValue(): Boolean {
        when (mode) {
            ADD -> return true
            VIEW -> return false
            else -> throw IllegalArgumentException()
        }
    }

    private fun titleValidInitialValue(): Boolean {
        when (mode) {
            ADD, VIEW -> return true
            else -> throw IllegalArgumentException()
        }
    }

    private fun handleSave() {
        when (mode) {
            ADD -> view.back()
            VIEW -> view.enableEditControls()
            else -> throw IllegalArgumentException()
        }
    }

    private fun handleCancel() {
        when (mode) {
            ADD -> view.back()
            VIEW -> {
                isEditing.set(false);
                view.enableEditControls();
                observableTrip.set(trip);
            }
            else -> throw IllegalArgumentException()
        }
    }

}