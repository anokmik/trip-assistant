package com.anokmik.tripassistant.util.contract.presenter

interface PresenterStartFinishDates {

    fun showStartDatePicker()

    fun showFinishDatePicker()

    fun setStartDate(startDate: Long)

    fun setFinishDate(finishDate: Long)

}