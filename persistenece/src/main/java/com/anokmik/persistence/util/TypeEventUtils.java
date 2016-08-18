package com.anokmik.persistence.util;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.IntDef;

import com.anokmik.persistence.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class TypeEventUtils {

    private TypeEventUtils() {

    }

    public static String getString(Context context, @Type int type) {
        switch (type) {
            case Type.DEFAULT:
                return context.getResources().getString(R.string.event_type_default);
            case Type.TICKET:
                return context.getResources().getString(R.string.event_type_ticket);
            case Type.HOTEL:
                return context.getResources().getString(R.string.event_type_hotel);
            case Type.MEETING:
                return context.getResources().getString(R.string.event_type_meeting);
            case Type.PLACE_OF_INTEREST:
                return context.getResources().getString(R.string.event_type_place_of_interest);
            default:
                return null;
        }
    }

    @IntDef({Type.DEFAULT, Type.TICKET, Type.HOTEL, Type.MEETING, Type.PLACE_OF_INTEREST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
        int DEFAULT = 0;
        int TICKET = 1;
        int HOTEL = 2;
        int MEETING = 3;
        int PLACE_OF_INTEREST = 4;
    }
}
