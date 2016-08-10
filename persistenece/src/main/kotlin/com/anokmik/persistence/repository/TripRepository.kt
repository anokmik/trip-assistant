package com.anokmik.persistence.repository

import com.anokmik.persistence.model.Trip

open class TripRepository : Repository<Trip>(Trip::class.java)