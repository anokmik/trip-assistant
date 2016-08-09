package com.anokmik.persistence.repository;

import com.anokmik.persistence.model.Trip;

public class TripRepository extends Repository<Trip> {

    @Override
    protected Class<Trip> getType() {
        return Trip.class;
    }

}