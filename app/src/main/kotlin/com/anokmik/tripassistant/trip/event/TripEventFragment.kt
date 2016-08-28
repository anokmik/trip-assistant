package com.anokmik.tripassistant

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.anokmik.tripassistant.base.BaseFragment
import com.anokmik.tripassistant.databinding.FragmentTripEventBinding
import com.anokmik.tripassistant.dialog.DatePickerDialogFragment
import com.anokmik.tripassistant.trip.*
import com.anokmik.tripassistant.trip.event.TripEventContract
import com.anokmik.tripassistant.trip.event.TripEventPresenter
import com.anokmik.tripassistant.user.UserActivity

class TripEventFragment : BaseFragment<FragmentTripEventBinding>(), TripEventContract.View {

    private lateinit var saveMenuItem: MenuItem
    private lateinit var cancelMenuItem: MenuItem
    private lateinit var editMenuItem: MenuItem
    private lateinit var deleteMenuItem: MenuItem

    private val mode by lazy { arguments.getLong(MODE) }

    private val tripId by lazy { arguments.getLong(TRIP_ID) }

    private val tripEventId by  lazy { arguments.getLong(TRIP_EVENT_ID) }

    //TODO: Mikle, implement
    private val takePhotoIntent: Intent? = null

    //TODO: Mikle, implement
    private val pickPhotoIntent: Intent? = null

    override val displayHomeAsUp = true

    override val titleResourceId = R.string.title_trip_event

    override val layoutId = R.layout.fragment_trip_event

    override val rowItemLayoutId = R.layout.row_photo_attachment

    override val itemBindingId = BR.photoAttachment

    override val itemListenerBindingId = BR.listener

    override val adapterPositionProviderBindingId = BR.positionProvider

    override val itemIsEditingBindingId = BR.isEditing

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        saveMenuItem = menu.findItem(R.id.action_save_trip_event)
        cancelMenuItem = menu.findItem(R.id.action_cancel)
        editMenuItem = menu.findItem(R.id.action_edit_trip_event)
        deleteMenuItem = menu.findItem(R.id.action_delete_trip_event)
        initControls()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_edit_trip_event -> {
                binding.presenter.edit()
                return true
            }
            R.id.action_cancel -> {
                binding.presenter.cancel()
                return true
            }
            R.id.action_save_trip_event -> {
                binding.presenter.save()
                return true
            }
            R.id.action_delete_trip_event -> {
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
        binding.presenter = TripEventPresenter(this, mode, tripId, tripEventId)
    }

    override fun getOptionMenuResourceId() = R.menu.menu_trip_event

    override fun showStartDatePickerDialog(startDate: Long?) {
        showDialog(DatePickerDialogFragment.startDateInstance(startDate, this))
    }

    override fun showFinishDatePickerDialog(finishDate: Long?) {
        showDialog(DatePickerDialogFragment.finishDateInstance(finishDate, this))
    }

    override fun showDatesInvalidError() {
        view?.apply {
            val snackbar = Snackbar.make(this, R.string.error_dates_invalid, Snackbar.LENGTH_LONG)
            snackbar.view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent))
            snackbar.show()
        }
    }

    override fun takePhotoAttachment() {
        launchActivity(takePhotoIntent)
    }

    override fun pickPhotoAttachment() {
        launchActivity(pickPhotoIntent)
    }

    override fun enableSaveControls() = showSaveControls()

    override fun enableEditControls() = showEditControls()

    override fun back() = activity.onBackPressed()

    override fun updateStartDate(startDate: Long) {
        binding.presenter.setStartDate(startDate)
    }

    override fun updateFinishDate(finishDate: Long) {
        binding.presenter.setFinishDate(finishDate)
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

        private val TAKE_PHOTO_REQUEST = 1212
        private val PICK_PHOTO_REQUEST = 2222

        fun add(tripId: Long): TripEventFragment {
            val arguments = Bundle()
            arguments.putLong(MODE, ADD)
            arguments.putLong(TRIP_ID, tripId)
            val tripEventFragment = TripEventFragment()
            tripEventFragment.arguments = arguments
            return tripEventFragment
        }

        fun view(tripEventId: Long): TripEventFragment {
            val arguments = Bundle()
            arguments.putLong(MODE, VIEW)
            arguments.putLong(TRIP_EVENT_ID, tripEventId)
            val tripDetailsFragment = TripEventFragment()
            tripDetailsFragment.arguments = arguments
            return tripDetailsFragment
        }

    }

}