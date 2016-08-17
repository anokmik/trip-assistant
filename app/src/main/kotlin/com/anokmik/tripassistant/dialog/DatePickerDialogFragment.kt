package com.anokmik.tripassistant.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.annotation.IntDef
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.widget.DatePicker

import com.anokmik.tripassistant.dialog.DateHandler

import java.util.Calendar

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val date by lazy { arguments.getLong(KEY_DATE) }

    private val type by lazy { arguments.getLong(KEY_TYPE) }

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calendar.timeInMillis = date
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(activity, this, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val targetFragment = targetFragment
        if (targetFragment is DateHandler) {
            when (type) {
                START -> targetFragment.updateStartDate(calendar.timeInMillis)
                FINISH -> targetFragment.updateFinishDate(calendar.timeInMillis)
            }
        }
    }

    @IntDef(START, FINISH)
    @Retention(AnnotationRetention.SOURCE)
    private annotation class Type

    companion object {

        private val KEY_DATE = "key_date"
        private val KEY_TYPE = "key_type"

        private const val START = 0L
        private const val FINISH = 1L

        fun startDateInstance(date: Long, target: Fragment): DatePickerDialogFragment {
            return newInstance(START, date, target)
        }

        fun finishDateInstance(date: Long, target: Fragment): DatePickerDialogFragment {
            return newInstance(FINISH, date, target)
        }

        private fun newInstance(@Type type: Long, date: Long, target: Fragment): DatePickerDialogFragment {
            val args = Bundle()
            args.putLong(KEY_DATE, date)
            args.putLong(KEY_TYPE, type)
            val fragment = DatePickerDialogFragment()
            fragment.arguments = args
            fragment.setTargetFragment(target, 0)
            return fragment
        }
    }

}