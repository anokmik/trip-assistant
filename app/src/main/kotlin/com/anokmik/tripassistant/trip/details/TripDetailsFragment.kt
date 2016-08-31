package com.anokmik.tripassistant.trip.details

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.anokmik.tripassistant.BR
import com.anokmik.tripassistant.R
import com.anokmik.tripassistant.TripEventFragment
import com.anokmik.tripassistant.base.BaseFragment
import com.anokmik.tripassistant.databinding.FragmentTripDetailsBinding
import com.anokmik.tripassistant.dialog.DatePickerDialogFragment
import com.anokmik.tripassistant.trip.ADD
import com.anokmik.tripassistant.trip.MODE
import com.anokmik.tripassistant.trip.TRIP_ID
import com.anokmik.tripassistant.trip.VIEW
import com.anokmik.tripassistant.user.UserActivity
import com.anokmik.tripassistant.util.ViewUtils
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration

class TripDetailsFragment : BaseFragment<FragmentTripDetailsBinding>(), TripDetailsContract.View {

    private lateinit var saveMenuItem: MenuItem
    private lateinit var cancelMenuItem: MenuItem
    private lateinit var editMenuItem: MenuItem
    private lateinit var deleteMenuItem: MenuItem

    private val mode by lazy { arguments.getLong(MODE) }

    private val tripId by lazy { arguments.getLong(TRIP_ID) }

    override val displayHomeAsUp = true

    override val titleResourceId = R.string.title_trip_details

    override val layoutId = R.layout.fragment_trip_details

    override val rowItemLayoutId = R.layout.row_trip_event

    override val itemBindingId = BR.tripEvent

    override val itemListenerBindingId = BR.listener

    override val adapterPositionProviderBindingId = BR.positionProvider

    override val itemIsEditingBindingId = BR.isEditing

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        saveMenuItem = menu.findItem(R.id.action_save_trip)
        cancelMenuItem = menu.findItem(R.id.action_cancel)
        editMenuItem = menu.findItem(R.id.action_edit_trip)
        deleteMenuItem = menu.findItem(R.id.action_delete_trip)
        initControls()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_edit_trip -> {
                binding.presenter.edit()
                return true
            }
            R.id.action_cancel -> {
                binding.presenter.cancel()
                return true
            }
            R.id.action_save_trip -> {
                binding.presenter.save()
                return true
            }
            R.id.action_delete_trip -> {
                binding.presenter.delete()
                return true
            }
            R.id.action_show_user -> {
                launchActivity(UserActivity::class.java)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun getOptionMenuResourceId() = R.menu.menu_trip_details

    override fun initBinding(binding: FragmentTripDetailsBinding) {
        binding.decoration = DividerItemDecoration(ContextCompat.getDrawable(context, R.drawable.small_divider))
        binding.layoutManager = LinearLayoutManager(context)
        binding.presenter = TripDetailsPresenter(this, mode, tripId)
    }

    override fun showStartDatePickerDialog(startDate: Long?) {
        showDialog(DatePickerDialogFragment.startDateInstance(startDate, this))
    }

    override fun showFinishDatePickerDialog(finishDate: Long?) {
        showDialog(DatePickerDialogFragment.finishDateInstance(finishDate, this))
    }

    override fun showDatesInvalidError() {
        ViewUtils.showSnackbar(view, R.string.error_dates_invalid)
    }

    override fun addTripEvent() {
        replaceFragment(TripEventFragment.add(tripId), null)
    }

    override fun viewTripEvent(tripEventId: Long) {
        replaceFragment(TripEventFragment.view(tripEventId), null)
    }

    override fun enableSaveControls() = showSaveControls()

    override fun enableEditControls() = showEditControls()

    override fun back() = activity.onBackPressed()

    override fun updateStartDate(startDate: Long) {
        binding.presenter.observableTrip.startDate = startDate
    }

    override fun updateFinishDate(finishDate: Long) {
        binding.presenter.observableTrip.finishDate = finishDate
    }

    private fun initControls() {
        when (mode) {
            ADD -> showAddControls()
            VIEW -> showEditControls()
        }
    }

    private fun showAddControls() = updateMenuItems(true, false, false)

    private fun showEditControls() = updateMenuItems(false, true, true)

    private fun showSaveControls() = updateMenuItems(true, false, true)

    private fun updateMenuItems(showSave: Boolean, showEdit: Boolean, showDelete: Boolean) {
        saveMenuItem.isVisible = showSave
        cancelMenuItem.isVisible = showSave
        editMenuItem.isVisible = showEdit
        deleteMenuItem.isVisible = showDelete
    }

    companion object {

        fun add(): TripDetailsFragment {
            val arguments = Bundle()
            arguments.putLong(MODE, ADD)
            val tripDetailsFragment = TripDetailsFragment()
            tripDetailsFragment.arguments = arguments
            return tripDetailsFragment
        }

        fun view(tripId: Long): TripDetailsFragment {
            val arguments = Bundle()
            arguments.putLong(MODE, VIEW)
            arguments.putLong(TRIP_ID, tripId)
            val tripDetailsFragment = TripDetailsFragment()
            tripDetailsFragment.arguments = arguments
            return tripDetailsFragment
        }

    }

}