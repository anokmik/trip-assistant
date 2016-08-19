package com.anokmik.tripassistant

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.anokmik.tripassistant.base.BaseFragment
import com.anokmik.tripassistant.databinding.FragmentTripEventBinding
import com.anokmik.tripassistant.dialog.DateHandler
import com.anokmik.tripassistant.dialog.DatePickerDialogFragment
import com.anokmik.tripassistant.trip.details.TripDetailsFragment
import com.anokmik.tripassistant.trip.event.TripEventContract
import com.anokmik.tripassistant.trip.event.TripEventPresenter
import com.anokmik.tripassistant.user.UserActivity

class TripEventFragment : BaseFragment<FragmentTripEventBinding>(), TripEventContract.View, DateHandler {

    private lateinit var saveMenuItem: MenuItem
    private lateinit var editMenuItem: MenuItem

    private val tripId by lazy { arguments.getLong(TripDetailsFragment.KEY_TRIP_ID) }

    private val tripEventId by  lazy { arguments.getLong(KEY_TRIP_EVENT_ID) }

    //TODO: Mikle, implement
    private val takePhotoIntent: Intent? = null

    //TODO: Mikle, implement
    private val pickPhotoIntent: Intent? = null

    override val displayHomeAsUp = true

    override val layoutId = R.layout.fragment_trip_event

    override val rowItemLayoutId = R.layout.row_photo_attachment

    override val itemBindingId = BR.photoAttachment

    override val itemListenerBindingId = BR.listener

    override val itemIsEditingBindingId = BR.isEditing

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setActionBarTitle(R.string.title_trip_event)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                TAKE_PHOTO_REQUEST -> {
                    //TODO: Mikle, get path to photo
                    val takePhotoPath: String? = null
                    binding.presenter.addPhotoAttachment(takePhotoPath)
                }
                PICK_PHOTO_REQUEST -> {
                    //TODO: Mikle, get path to photo
                    val pickPhotoPath: String? = null
                    binding.presenter.addPhotoAttachment(pickPhotoPath)
                }
            }
        }
    }


    override fun initBinding(binding: FragmentTripEventBinding) {
        binding.setPresenter(TripEventPresenter(this, tripEventId))
    }

    override fun getOptionMenuResourceId() = R.menu.menu_trip_event

    override fun showStartDatePickerDialog(startDate: Long) {
        showDialog(DatePickerDialogFragment.startDateInstance(startDate, this))
    }

    override fun showFinishDatePickerDialog(finishDate: Long) {
        showDialog(DatePickerDialogFragment.finishDateInstance(finishDate, this))
    }

    override fun takePhotoAttachment() {
        launchActivity(takePhotoIntent)
    }

    override fun pickPhotoAttachment() {
        launchActivity(pickPhotoIntent)
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

        val KEY_TRIP_EVENT_ID = "key_trip_event_id"

        private val TAKE_PHOTO_REQUEST = 1212
        private val PICK_PHOTO_REQUEST = 2222

        fun add(tripId: Long): TripEventFragment {
            val arguments = Bundle()
            arguments.putLong(TripDetailsFragment.KEY_TRIP_ID, tripId)
            val tripEventFragment = TripEventFragment()
            tripEventFragment.arguments = arguments
            return tripEventFragment
        }

        fun view(tripEventId: Long): TripEventFragment {
            val arguments = Bundle()
            arguments.putLong(KEY_TRIP_EVENT_ID, tripEventId)
            val tripDetailsFragment = TripEventFragment()
            tripDetailsFragment.arguments = arguments
            return tripDetailsFragment
        }

    }

}