package com.anokmik.tripassistant.model;

import android.databinding.BaseObservable;

import com.anokmik.persistence.model.TripEvent;

public final class ObservableTripEvent extends BaseObservable {

    private TripEvent tripEvent;

    public ObservableTripEvent(TripEvent tripEvent) {
        this.tripEvent = tripEvent;
    }

    public TripEvent get() {
        return tripEvent;
    }

    public void set(TripEvent tripEvent) {
        this.tripEvent = tripEvent;
        notifyChange();
    }

    public String getName() {
        return tripEvent.name;
    }

    public void setName(String name) {
        tripEvent.name = name;
    }

    public String getComment() {
        return tripEvent.comment;
    }

    public void setComment(String comment) {
        tripEvent.comment = comment;
    }

    public long getStartDate() {
        return tripEvent.startDate;
    }

    public void setStartDate(long startDate) {
        tripEvent.startDate = startDate;
        notifyChange();
    }

    public long getFinishDate() {
        return tripEvent.finishDate;
    }

    public void setFinishDate(long finishDate) {
        tripEvent.finishDate = finishDate;
        notifyChange();
    }

    public void save() {
        tripEvent.save();
    }

    public void delete() {
        tripEvent.delete();
    }

}