package com.anokmik.tripassistant.trip.event

import android.databinding.ObservableBoolean
import com.anokmik.persistence.model.PhotoAttachment
import com.anokmik.persistence.model.PhotoAttachment_Table
import com.anokmik.persistence.model.TripEvent_Table
import com.anokmik.persistence.repository.PhotoAttachmentRepository
import com.anokmik.persistence.repository.TripEventRepository
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter

class TripEventPresenter(private val view: TripEventContract.View, tripEventId: Long) : TripEventContract.Presenter, TripEventContract.PhotoAttachmentListener {

    val isEditing = ObservableBoolean(tripEventId == 0L)
    val nameValid = ObservableBoolean(tripEventId != 0L)

    override val tripEvent = TripEventRepository().get(TripEvent_Table.id.`is`(tripEventId))

    override val photoAttachments = PhotoAttachmentRepository().getList(PhotoAttachment_Table.tripEvent.`is`(tripEventId))

    override val viewHolderPresenter: ViewHolderPresenter<PhotoAttachment>
        get() = ViewHolderPresenter.Builder<PhotoAttachment>(view.rowItemLayoutId, view.itemBindingId).mapVariable(view.itemListenerBindingId, this).mapVariable(view.itemIsEditingBindingId, isEditing).build()

    override fun showStartDatePicker() {
        tripEvent?.let {
            view.showStartDatePickerDialog(tripEvent.startDate)
        }
    }

    override fun showFinishDatePicker() {
        tripEvent?.let {
            view.showFinishDatePickerDialog(tripEvent.finishDate)
        }
    }

    override fun setStartDate(startDate: Long) {
        tripEvent?.startDate = startDate
    }

    override fun setFinishDate(finishDate: Long) {
        tripEvent?.finishDate = finishDate
    }

    override fun save() {
        isEditing.set(false)
        tripEvent?.save()
        view.enableEditMode()
    }

    override fun edit() {
        isEditing.set(true)
        view.enableSaveMode()
    }

    override fun delete() {
        for (photoAttachment in photoAttachments) {
            photoAttachment.delete()
        }
        tripEvent?.delete()
    }

    override fun addPhotoAttachment(path: String?) {
        path?.let {
            val photoAttachment = PhotoAttachment()
            photoAttachment.tripEvent = tripEvent
            photoAttachment.path = path
            photoAttachment.save()
        }
    }

    override fun takePhoto() {
        view.takePhotoAttachment()
    }

    override fun pickPhoto() {
        view.pickPhotoAttachment()
    }

    override fun deletePhoto(photoAttachment: PhotoAttachment) {
        photoAttachment.delete()
    }

    var tripEventName: String?
        get() = tripEvent?.name
        set(name) {
            tripEvent?.name = name
        }

    var tripEventComment: String?
        get() = tripEvent?.comment
        set(comment) {
            tripEvent?.comment = comment
        }

    val tripEventStartDate: Long?
        get() = tripEvent?.startDate

    val tripEventFinishDate: Long?
        get() = tripEvent?.finishDate

}