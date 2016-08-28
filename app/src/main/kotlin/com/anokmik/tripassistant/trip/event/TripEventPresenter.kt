package com.anokmik.tripassistant.trip.event

import android.databinding.ObservableBoolean
import com.anokmik.persistence.model.PhotoAttachment
import com.anokmik.persistence.model.TripEvent
import com.anokmik.persistence.model.Trip_Table
import com.anokmik.persistence.model.TripEvent_Table
import com.anokmik.persistence.model.PhotoAttachment_Table
import com.anokmik.persistence.repository.PhotoAttachmentRepository
import com.anokmik.persistence.repository.TripEventRepository
import com.anokmik.persistence.repository.TripRepository
import com.anokmik.persistence.repository.UserRepository
import com.anokmik.tripassistant.databinding.ObservableCompositeList
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter
import com.anokmik.tripassistant.model.ObservableTripEvent
import com.anokmik.tripassistant.trip.ADD
import com.anokmik.tripassistant.trip.Mode
import com.anokmik.tripassistant.trip.VIEW
import com.anokmik.tripassistant.util.DateUtils
import com.anokmik.tripassistant.validator.TextLengthValidator

class TripEventPresenter(private val view: TripEventContract.View, @Mode val mode: Long, private val tripId: Long, private val tripEventId: Long) : TripEventContract.Presenter {

    val isEditing = ObservableBoolean(isEditingInitialValue())
    val nameValid = ObservableBoolean(nameValidInitialValue())

    private val tripRepository = TripRepository()
    private val tripEventRepository = TripEventRepository()
    private val photoAttachmentRepository = PhotoAttachmentRepository()
    private val validator = TextLengthValidator()

    private val tripEvent: TripEvent?
        get() {
            when (mode) {
                ADD -> {
                    val tripEvent = TripEvent()
                    UserRepository().getActive()?.apply {
                        tripEvent.user = this
                    }
                    tripEvent.startDate = System.currentTimeMillis()
                    tripEvent.finishDate = System.currentTimeMillis()
                    tripEvent.trip = tripRepository.get(Trip_Table.id.`is`(tripId))
                    return tripEvent
                }
                VIEW -> return tripEventRepository.get(TripEvent_Table.id.`is`(tripEventId))
                else -> throw IllegalArgumentException()
            }
        }

    val observableTripEvent = ObservableTripEvent(tripEvent)

    override val photoAttachments = ObservableCompositeList(photoAttachmentRepository.getAsyncList(PhotoAttachment_Table.tripEvent.`is`(tripEventId)))

    override val viewHolderPresenter: ViewHolderPresenter<PhotoAttachment>
        get() = ViewHolderPresenter.Builder<PhotoAttachment>(view.rowItemLayoutId, view.itemBindingId)
                .setAdapterPositionProviderBindingId(view.adapterPositionProviderBindingId)
                .mapVariable(view.itemListenerBindingId, this)
                .mapVariable(view.itemIsEditingBindingId, isEditing)
                .build()

    override fun showStartDatePicker() = view.showStartDatePickerDialog(observableTripEvent.startDate)

    override fun showFinishDatePicker() = view.showFinishDatePickerDialog(observableTripEvent.finishDate)

    override fun setStartDate(startDate: Long) {
        observableTripEvent.startDate = startDate
    }

    override fun setFinishDate(finishDate: Long) {
        observableTripEvent.finishDate = finishDate
    }

    override fun validFields(): Boolean {
        nameValid.set(validator.notEmpty(observableTripEvent.name))
        val validDates = DateUtils.validDates(observableTripEvent.startDate, observableTripEvent.finishDate)
        if (!validDates) {
            view.showDatesInvalidError()
        }
        return nameValid.get() && validDates
    }

    override fun save() {
        if (validFields()) {
            isEditing.set(false)
            observableTripEvent.save()
            handleSave()
        }
    }

    override fun cancel() = handleCancel()

    override fun edit() {
        isEditing.set(true)
        view.enableSaveControls()
    }

    override fun delete() {
        for (photoAttachment in photoAttachments) {
            photoAttachment.delete()
        }
        observableTripEvent.delete()
        view.back()
    }

    override fun addPhotoAttachment(path: String?) {
        path?.let {
            val photoAttachment = PhotoAttachment()
            photoAttachment.tripEvent = tripEvent
            photoAttachment.path = path
            photoAttachment.save()
        }
    }

    override fun takePhoto() = view.takePhotoAttachment()

    override fun pickPhoto() = view.pickPhotoAttachment()

    override fun deletePhoto(position: Int) {
        photoAttachments.remove(photoAttachments[position])
    }

    private fun isEditingInitialValue(): Boolean {
        when (mode) {
            ADD -> return true
            VIEW -> return false
            else -> throw IllegalArgumentException()
        }
    }

    private fun nameValidInitialValue(): Boolean {
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
                isEditing.set(false)
                view.enableEditControls()
                observableTripEvent.set(tripEvent)
            }
            else -> throw IllegalArgumentException()
        }
    }

}