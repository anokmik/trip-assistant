package com.anokmik.tripassistant.model

import android.databinding.BaseObservable

import com.anokmik.persistence.model.TripEvent

class ObservableTripEvent(private var tripEvent: TripEvent?) : BaseObservable() {

    fun get(): TripEvent? {
        return tripEvent
    }

    fun set(tripEvent: TripEvent?) {
        this.tripEvent = tripEvent
        notifyChange()
    }

    var name: String?
        get() = tripEvent?.name
        set(name) {
            tripEvent?.name = name
        }

    var comment: String?
        get() = tripEvent?.comment
        set(comment) {
            tripEvent?.comment = comment
        }

    var startDate: Long?
        get() = tripEvent?.startDate
        set(startDate) {
            tripEvent?.startDate = startDate
            notifyChange()
        }

    var finishDate: Long?
        get() = tripEvent?.finishDate
        set(finishDate) {
            tripEvent?.finishDate = finishDate
            notifyChange()
        }

    fun save() = tripEvent?.save()

    fun delete() = tripEvent?.delete()

}