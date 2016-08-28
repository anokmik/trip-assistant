package com.anokmik.tripassistant.util.contract.view

interface ViewStartFinishDates {

    fun showStartDatePickerDialog(startDate: Long?)

    fun showFinishDatePickerDialog(finishDate: Long?)

    fun showDatesInvalidError()

}