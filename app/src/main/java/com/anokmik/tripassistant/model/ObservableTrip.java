package com.anokmik.tripassistant.model;

import android.databinding.BaseObservable;

import com.anokmik.persistence.model.Trip;

public final class ObservableTrip extends BaseObservable {

    private Trip trip;

    public ObservableTrip(Trip trip) {
        this.trip = trip;
    }

    public Trip get() {
        return trip;
    }

    public void set(Trip trip) {
        this.trip = trip;
        notifyChange();
    }

    public String getTitle() {
        return trip.title;
    }

    public void setTitle(String title) {
        trip.title = title;
    }

    public String getDescription() {
        return trip.description;
    }

    public void setDescription(String description) {
        trip.description = description;
    }

    public long getStartDate() {
        return trip.startDate;
    }

    public void setStartDate(long startDate) {
        trip.startDate = startDate;
        notifyChange();
    }

    public long getFinishDate() {
        return trip.finishDate;
    }

    public void setFinishDate(long finishDate) {
        trip.finishDate = finishDate;
        notifyChange();
    }

    public void save() {
        trip.save();
    }

    public void delete() {
        trip.delete();
    }

}