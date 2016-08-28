package com.anokmik.tripassistant.model

import android.databinding.BaseObservable

import com.anokmik.persistence.model.Trip

class ObservableTrip(private var trip: Trip?) : BaseObservable() {

    fun get(): Trip? {
        return trip
    }

    fun set(trip: Trip?) {
        this.trip = trip
        notifyChange()
    }

    var title: String?
        get() = trip?.title
        set(title) {
            trip?.title = title
        }

    var description: String?
        get() = trip?.description
        set(description) {
            trip?.description = description
        }

    var startDate: Long?
        get() = trip?.startDate
        set(startDate) {
            trip?.startDate = startDate
            notifyChange()
        }

    var finishDate: Long?
        get() = trip?.finishDate
        set(finishDate) {
            trip?.finishDate = finishDate
            notifyChange()
        }

    fun save() = trip?.save()

    fun delete() = trip?.delete()

}