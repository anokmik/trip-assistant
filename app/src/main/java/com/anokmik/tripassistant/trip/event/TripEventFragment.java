package com.anokmik.tripassistant.trip.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.anokmik.tripassistant.BR;
import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.base.BaseFragment;
import com.anokmik.tripassistant.databinding.FragmentTripEventBinding;
import com.anokmik.tripassistant.dialog.DatePickerDialogFragment;
import com.anokmik.tripassistant.trip.Key;
import com.anokmik.tripassistant.trip.Mode;
import com.anokmik.tripassistant.user.UserActivity;
import com.anokmik.tripassistant.util.ViewUtils;

public final class TripEventFragment extends BaseFragment<FragmentTripEventBinding> implements TripEventContract.View {

    private static final int TAKE_PHOTO_REQUEST = 1212;
    private static final int PICK_PHOTO_REQUEST = 2222;

    private MenuItem saveMenuItem;
    private MenuItem cancelMenuItem;
    private MenuItem editMenuItem;
    private MenuItem deleteMenuItem;

    public static TripEventFragment add(long tripId) {
        Bundle arguments = new Bundle();
        arguments.putInt(Key.MODE, Mode.ADD);
        arguments.putLong(Key.TRIP_ID, tripId);
        TripEventFragment tripEventFragment = new TripEventFragment();
        tripEventFragment.setArguments(arguments);
        return tripEventFragment;
    }

    public static TripEventFragment view(long tripEventId) {
        Bundle arguments = new Bundle();
        arguments.putInt(Key.MODE, Mode.VIEW);
        arguments.putLong(Key.TRIP_EVENT_ID, tripEventId);
        TripEventFragment tripDetailsFragment = new TripEventFragment();
        tripDetailsFragment.setArguments(arguments);
        return tripDetailsFragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        saveMenuItem = menu.findItem(R.id.action_save_trip_event);
        cancelMenuItem = menu.findItem(R.id.action_cancel);
        editMenuItem = menu.findItem(R.id.action_edit_trip_event);
        deleteMenuItem = menu.findItem(R.id.action_delete_trip_event);
        initControls();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_trip_event:
                getBinding().getPresenter().edit();
                return true;
            case R.id.action_cancel:
                getBinding().getPresenter().cancel();
                return true;
            case R.id.action_save_trip_event:
                getBinding().getPresenter().save();
                return true;
            case R.id.action_delete_trip_event:
                getBinding().getPresenter().delete();
                return true;
            case R.id.action_show_user:
                launchActivity(UserActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //TODO: Implement path retrieving for picked or taken photo
            switch (requestCode) {
                case TAKE_PHOTO_REQUEST:
                    String takePhotoPath = null;
                    getBinding().getPresenter().addPhotoAttachment(takePhotoPath);
                    break;
                case PICK_PHOTO_REQUEST:
                    String pickPhotoPath = null;
                    getBinding().getPresenter().addPhotoAttachment(pickPhotoPath);
                    break;
            }
        }
    }

    @Override
    protected boolean displayHomeAsUp() {
        return true;
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.title_trip_event;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trip_event;
    }

    @Override
    protected void initBinding(FragmentTripEventBinding binding) {
        binding.setPresenter(new TripEventPresenter(this, getMode(), getTripId(), getTripEventId()));
    }

    @Override
    protected int getOptionMenuResourceId() {
        return R.menu.menu_trip_event;
    }

    @Override
    public int getRowItemLayoutId() {
        return R.layout.row_photo_attachment;
    }

    @Override
    public int getItemBindingId() {
        return BR.photoAttachment;
    }

    @Override
    public int getAdapterPositionProviderBindingId() {
        return BR.positionProvider;
    }

    @Override
    public int getItemListenerBindingId() {
        return BR.listener;
    }

    @Override
    public int getItemIsEditingBindingId() {
        return BR.isEditing;
    }

    @Override
    public void showStartDatePickerDialog(long startDate) {
        showDialog(DatePickerDialogFragment.startDateInstance(startDate, this));
    }

    @Override
    public void showFinishDatePickerDialog(long finishDate) {
        showDialog(DatePickerDialogFragment.finishDateInstance(finishDate, this));
    }

    @Override
    public void showDatesInvalidError() {
        ViewUtils.showSnackbar(getView(), R.string.error_dates_invalid);
    }

    @Override
    public void takePhotoAttachment() {
        launchActivity(getTakePhotoIntent(), TAKE_PHOTO_REQUEST);
    }

    @Override
    public void pickPhotoAttachment() {
        launchActivity(getPickPhotoIntent(), PICK_PHOTO_REQUEST);
    }

    @Override
    public void enableSaveControls() {
        showSaveControls();
    }

    @Override
    public void enableEditControls() {
        showEditControls();
    }

    @Override
    public void back() {
        getActivity().onBackPressed();
    }

    @Override
    public void updateStartDate(long startDate) {
        getBinding().getPresenter().setStartDate(startDate);
    }

    @Override
    public void updateFinishDate(long finishDate) {
        getBinding().getPresenter().setFinishDate(finishDate);
    }

    private void initControls() {
        switch (getMode()) {
            case Mode.ADD:
                showAddControls();
                break;
            case Mode.VIEW:
                showEditControls();
                break;
        }
    }

    private void showAddControls() {
        updateMenuItems(true, false, false);
    }

    private void showEditControls() {
        updateMenuItems(false, true, true);
    }

    private void showSaveControls() {
        updateMenuItems(true, false, true);
    }

    private void updateMenuItems(boolean showSave, boolean showEdit, boolean showDelete) {
        saveMenuItem.setVisible(showSave);
        cancelMenuItem.setVisible(showSave);
        editMenuItem.setVisible(showEdit);
        deleteMenuItem.setVisible(showDelete);
    }

    private Intent getTakePhotoIntent() {
        ViewUtils.showSnackbar(getView(), R.string.feature_that_require_implementation);
        //TODO: Build intent for taking photo
        return null;
    }

    private Intent getPickPhotoIntent() {
        ViewUtils.showSnackbar(getView(), R.string.feature_that_require_implementation);
        //TODO: Build intent for picking photo
        return null;
    }

    @SuppressWarnings("WrongConstant")
    @Mode
    private int getMode() {
        return getArguments().getInt(Key.MODE);
    }

    private long getTripId() {
        return getArguments().getLong(Key.TRIP_ID);
    }

    private long getTripEventId() {
        return getArguments().getLong(Key.TRIP_EVENT_ID);
    }

}