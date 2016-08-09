package com.anokmik.persistence.repository;

import com.anokmik.persistence.model.TripEvent;

public class TripEventRepository extends Repository<TripEvent> {

    @Override
    protected Class<TripEvent> getType() {
        return TripEvent.class;
    }

}