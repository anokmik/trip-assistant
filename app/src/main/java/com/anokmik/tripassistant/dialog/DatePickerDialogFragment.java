package com.anokmik.tripassistant.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import com.anokmik.tripassistant.trip.Key;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private final Calendar calendar = Calendar.getInstance();

    public static DatePickerDialogFragment startDateInstance(long date, Fragment target) {
        return newInstance(Type.START, date, target);
    }

    public static DatePickerDialogFragment finishDateInstance(long date, Fragment target) {
        return newInstance(Type.FINISH, date, target);
    }

    private static DatePickerDialogFragment newInstance(@Type int type, long date, Fragment target) {
        Bundle args = new Bundle();
        args.putInt(Key.TYPE, type);
        args.putLong(Key.DATE, date);
        DatePickerDialogFragment fragment = new DatePickerDialogFragment();
        fragment.setArguments(args);
        fragment.setTargetFragment(target, 0);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar.setTimeInMillis(getDate());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), this, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        Fragment targetFragment = getTargetFragment();
        if (targetFragment instanceof DateHandler) {
            switch (getType()) {
                case Type.START:
                    ((DateHandler) targetFragment).updateStartDate(calendar.getTimeInMillis());
                    break;
                case Type.FINISH:
                    ((DateHandler) targetFragment).updateFinishDate(calendar.getTimeInMillis());
                    break;
            }
        }
    }

    private long getDate() {
        return getArguments().getLong(Key.DATE);
    }

    @SuppressWarnings("WrongConstant")
    @Type
    private int getType() {
        return getArguments().getInt(Key.TYPE);
    }

    @IntDef({Type.START, Type.FINISH})
    @Retention(RetentionPolicy.SOURCE)
    private @interface Type {
        int START = 1;
        int FINISH = 2;
    }

}