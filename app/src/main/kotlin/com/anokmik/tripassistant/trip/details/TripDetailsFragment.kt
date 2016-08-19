package com.anokmik.tripassistant.trip.details

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.anokmik.tripassistant.BR
import com.anokmik.tripassistant.R
import com.anokmik.tripassistant.TripEventFragment
import com.anokmik.tripassistant.base.BaseFragment
import com.anokmik.tripassistant.databinding.FragmentTripDetailsBinding
import com.anokmik.tripassistant.dialog.DateHandler
import com.anokmik.tripassistant.dialog.DatePickerDialogFragment
import com.anokmik.tripassistant.user.UserActivity

class TripDetailsFragment : BaseFragment<FragmentTripDetailsBinding>(), TripDetailsContract.View, DateHandler {

    private lateinit var saveMenuItem: MenuItem
    private lateinit var editMenuItem: MenuItem

    private val tripId by lazy { arguments.getLong(KEY_TRIP_ID) }

    override val displayHomeAsUp = true

    override val layoutId = R.layout.fragment_trip_details

    override val rowItemLayoutId = R.layout.row_trip_event

    override val itemBindingId = BR.tripEvent

    override val itemListenerBindingId = BR.listener

    override val itemIsEditingBindingId = BR.isEditing

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setActionBarTitle(R.string.title_trip_details)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        saveMenuItem = menu.findItem(R.id.action_save_trip)
        editMenuItem = menu.findItem(R.id.action_edit_trip)
        showEditMenuItem()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_edit_trip -> {
                binding.presenter.edit()
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
            R.id.action_user -> {
                launchActivity(UserActivity::class.java)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun getOptionMenuResourceId() = R.menu.menu_trip_details

    override fun initBinding(binding: FragmentTripDetailsBinding) {
        binding.layoutManager = LinearLayoutManager(context)
        binding.presenter = TripDetailsPresenter(this, tripId)
    }

    override fun showStartDatePickerDialog(startDate: Long) {
        showDialog(DatePickerDialogFragment.startDateInstance(startDate, this))
    }

    override fun showFinishDatePickerDialog(finishDate: Long) {
        showDialog(DatePickerDialogFragment.finishDateInstance(finishDate, this))
    }

    override fun addTripEvent() {
        replaceFragment(TripEventFragment.add(tripId), null)
    }

    override fun viewTripEvent(tripEventId: Long) {
        replaceFragment(TripEventFragment.view(tripEventId), null)
    }

    override fun enableSaveMode() {
        showSaveMenuItem()
    }

    override fun enableEditMode() {
        showEditMenuItem()
    }

    override fun back() {
        activity.onBackPressed()
    }

    override fun updateStartDate(startDate: Long) {
        binding.presenter.setStartDate(startDate)
    }

    override fun updateFinishDate(finishDate: Long) {
        binding.presenter.setFinishDate(finishDate)
    }

    private fun showSaveMenuItem() {
        updateModeMenuItems(true, false)
    }

    private fun showEditMenuItem() {
        updateModeMenuItems(false, true)
    }

    private fun updateModeMenuItems(showSave: Boolean, showEdit: Boolean) {
        saveMenuItem.isVisible = showSave
        editMenuItem.isVisible = showEdit
    }

    companion object {

        val KEY_TRIP_ID = "key_trip_id"

        fun add(): TripDetailsFragment {
            return TripDetailsFragment()
        }

        fun view(tripId: Long): TripDetailsFragment {
            val arguments = Bundle()
            arguments.putLong(KEY_TRIP_ID, tripId)
            val tripDetailsFragment = TripDetailsFragment()
            tripDetailsFragment.arguments = arguments
            return tripDetailsFragment
        }
    }

}