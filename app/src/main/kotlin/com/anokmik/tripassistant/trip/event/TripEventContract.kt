package com.anokmik.tripassistant.trip.event

import android.support.annotation.LayoutRes

import com.anokmik.persistence.model.PhotoAttachment
import com.anokmik.persistence.model.TripEvent
import com.anokmik.tripassistant.databinding.adapter.ViewHolderPresenter

interface TripEventContract {

    interface Presenter {

        val tripEvent: TripEvent?

        val photoAttachments: List<PhotoAttachment>

        val viewHolderPresenter: ViewHolderPresenter<PhotoAttachment>

        fun showStartDatePicker()

        fun showFinishDatePicker()

        fun setStartDate(startDate: Long)

        fun setFinishDate(finishDate: Long)

        fun save()

        fun edit()

        fun delete()

        fun addPhotoAttachment(path: String?)

    }

    interface PhotoAttachmentListener {

        fun takePhoto()

        fun pickPhoto()

        fun deletePhoto(photoAttachment: PhotoAttachment)

    }

    interface View {

        val rowItemLayoutId: Int

        val itemBindingId: Int

        val itemListenerBindingId: Int

        val itemIsEditingBindingId: Int

        fun showStartDatePickerDialog(startDate: Long)

        fun showFinishDatePickerDialog(finishDate: Long)

        fun takePhotoAttachment()

        fun pickPhotoAttachment()

        fun enableSaveMode()

        fun enableEditMode()

        fun back()

    }

}