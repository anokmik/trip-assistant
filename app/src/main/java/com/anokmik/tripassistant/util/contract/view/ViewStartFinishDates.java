package com.anokmik.tripassistant.util.contract.view;

public interface ViewStartFinishDates {

    void showStartDatePickerDialog(long startDate);

    void showFinishDatePickerDialog(long finishDate);

    void showDatesInvalidError();

}