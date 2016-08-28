package com.anokmik.tripassistant.trip.event

interface PhotoAttachmentListener {

    fun addPhotoAttachment(path: String?)

    fun takePhoto()

    fun pickPhoto()

    fun deletePhoto(position: Int)

}