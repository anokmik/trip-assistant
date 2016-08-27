package com.anokmik.tripassistant.trip.event;

public interface PhotoAttachmentListener {

    void takePhoto();

    void pickPhoto();

    void deletePhoto(int position);

}