package com.anokmik.persistence.repository

import com.anokmik.persistence.model.TripEvent

open class TripEventRepository : Repository<TripEvent>(TripEvent::class.java)