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
import com.anokmik.tripassistant.dialog.DateHandler;
import com.anokmik.tripassistant.dialog.DatePickerDialogFragment;
import com.anokmik.tripassistant.trip.details.TripDetailsFragment;
import com.anokmik.tripassistant.user.UserActivity;

public final class TripEventFragment extends BaseFragment<FragmentTripEventBinding> implements TripEventContract.View, DateHandler {

    public static final String KEY_TRIP_EVENT_ID = "key_trip_event_id";

    private static final int TAKE_PHOTO_REQUEST = 1212;
    private static final int PICK_PHOTO_REQUEST = 2222;

    private MenuItem saveMenuItem;
    private MenuItem editMenuItem;

    public static TripEventFragment add(long tripId) {
        Bundle arguments = new Bundle();
        arguments.putLong(TripDetailsFragment.KEY_TRIP_ID, tripId);
        TripEventFragment tripEventFragment = new TripEventFragment();
        tripEventFragment.setArguments(arguments);
        return tripEventFragment;
    }

    public static TripEventFragment view(long tripEventId) {
        Bundle arguments = new Bundle();
        arguments.putLong(KEY_TRIP_EVENT_ID, tripEventId);
        TripEventFragment tripDetailsFragment = new TripEventFragment();
        tripDetailsFragment.setArguments(arguments);
        return tripDetailsFragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        saveMenuItem = menu.findItem(R.id.action_save_trip_event);
        editMenuItem = menu.findItem(R.id.action_edit_trip_event);
        showEditMenuItem();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_trip_event:
                getBinding().getPresenter().edit();
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
            switch (requestCode) {
                case TAKE_PHOTO_REQUEST:
                    //TODO: Mikle, get path to photo
                    String takePhotoPath = null;
                    getBinding().getPresenter().addPhotoAttachment(takePhotoPath);
                    break;
                case PICK_PHOTO_REQUEST:
                    //TODO: Mikle, get path to photo
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
        binding.setPresenter(new TripEventPresenter(this, getTripEventId()));
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
    public void takePhotoAttachment() {
        launchActivity(getTakePhotoIntent());
    }

    @Override
    public void pickPhotoAttachment() {
        launchActivity(getPickPhotoIntent());
    }

    @Override
    public void enableSaveMode() {
        showSaveMenuItem();
    }

    @Override
    public void enableEditMode() {
        showEditMenuItem();
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

    private void showSaveMenuItem() {
        updateModeMenuItems(true, false);
    }

    private void showEditMenuItem() {
        updateModeMenuItems(false, true);
    }

    private void updateModeMenuItems(boolean showSave, boolean showEdit) {
        saveMenuItem.setVisible(showSave);
        editMenuItem.setVisible(showEdit);
    }

    private Intent getTakePhotoIntent() {
        //TODO: Mikle, implement
        return null;
    }

    private Intent getPickPhotoIntent() {
        //TODO: Mikle, implement
        return null;
    }

    private long getTripId() {
        return getArguments().getLong(TripDetailsFragment.KEY_TRIP_ID);
    }

    private long getTripEventId() {
        return getArguments().getLong(KEY_TRIP_EVENT_ID);
    }

}