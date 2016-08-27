package com.anokmik.tripassistant.util.contract.presenter;

public interface PresenterStartFinishDates {

    void showStartDatePicker();

    void showFinishDatePicker();

    void setStartDate(long startDate);

    void setFinishDate(long finishDate);

}