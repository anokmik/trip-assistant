package com.anokmik.tripassistant.trip.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.anokmik.tripassistant.BR;
import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.base.BaseFragment;
import com.anokmik.tripassistant.databinding.FragmentTripDetailsBinding;
import com.anokmik.tripassistant.dialog.DateHandler;
import com.anokmik.tripassistant.dialog.DatePickerDialogFragment;
import com.anokmik.tripassistant.trip.event.TripEventFragment;
import com.anokmik.tripassistant.user.UserActivity;

public final class TripDetailsFragment extends BaseFragment<FragmentTripDetailsBinding> implements TripDetailsContract.View, DateHandler {

    public static final String KEY_TRIP_ID = "key_trip_id";

    private MenuItem saveMenuItem;
    private MenuItem editMenuItem;

    public static TripDetailsFragment add() {
        return new TripDetailsFragment();
    }

    public static TripDetailsFragment view(long tripId) {
        Bundle arguments = new Bundle();
        arguments.putLong(KEY_TRIP_ID, tripId);
        TripDetailsFragment tripDetailsFragment = new TripDetailsFragment();
        tripDetailsFragment.setArguments(arguments);
        return tripDetailsFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setActionBarTitle(R.string.title_trip_details);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        saveMenuItem = menu.findItem(R.id.action_save_trip);
        editMenuItem = menu.findItem(R.id.action_edit_trip);
        showEditMenuItem();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_trip:
                showSaveMenuItem();
                getBinding().getPresenter().edit();
                return true;
            case R.id.action_save_trip:
                showEditMenuItem();
                getBinding().getPresenter().save();
                immediatePopBack(FragmentManager.POP_BACK_STACK_INCLUSIVE, null);
                return true;
            case R.id.action_delete_trip:
                getBinding().getPresenter().delete();
                immediatePopBack(FragmentManager.POP_BACK_STACK_INCLUSIVE, null);
                return true;
            case R.id.action_user:
                launchActivity(UserActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected boolean displayHomeAsUp() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trip_details;
    }

    @Override
    protected void initBinding(FragmentTripDetailsBinding binding) {
        binding.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.setPresenter(new TripDetailsPresenter(this, getTripId()));
    }

    @Override
    protected int getOptionMenuResourceId() {
        return R.menu.menu_trip_details;
    }

    @Override
    public int getRowItemLayoutId() {
        return R.layout.row_trip_event;
    }

    @Override
    public int getItemBindingId() {
        return BR.tripEvent;
    }

    @Override
    public int getItemListenerBindingId() {
        return BR.tripEventListener;
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
    public void addTripEvent() {
        replaceFragment(TripEventFragment.add(getTripId()), null);
    }

    @Override
    public void viewTripEvent(long tripEventId) {
        replaceFragment(TripEventFragment.view(tripEventId), null);
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

    private long getTripId() {
        return getArguments().getLong(KEY_TRIP_ID);
    }

}