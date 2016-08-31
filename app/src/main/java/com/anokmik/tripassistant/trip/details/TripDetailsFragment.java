package com.anokmik.tripassistant.trip.details;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.anokmik.tripassistant.BR;
import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.base.BaseFragment;
import com.anokmik.tripassistant.databinding.FragmentTripDetailsBinding;
import com.anokmik.tripassistant.dialog.DatePickerDialogFragment;
import com.anokmik.tripassistant.trip.Key;
import com.anokmik.tripassistant.trip.Mode;
import com.anokmik.tripassistant.trip.event.TripEventFragment;
import com.anokmik.tripassistant.user.UserActivity;
import com.anokmik.tripassistant.util.ViewUtils;
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;

public final class TripDetailsFragment extends BaseFragment<FragmentTripDetailsBinding> implements TripDetailsContract.View {

    private MenuItem saveMenuItem;
    private MenuItem cancelMenuItem;
    private MenuItem editMenuItem;
    private MenuItem deleteMenuItem;

    public static TripDetailsFragment add() {
        Bundle arguments = new Bundle();
        arguments.putInt(Key.MODE, Mode.ADD);
        TripDetailsFragment tripDetailsFragment = new TripDetailsFragment();
        tripDetailsFragment.setArguments(arguments);
        return tripDetailsFragment;
    }

    public static TripDetailsFragment view(long tripId) {
        Bundle arguments = new Bundle();
        arguments.putInt(Key.MODE, Mode.VIEW);
        arguments.putLong(Key.TRIP_ID, tripId);
        TripDetailsFragment tripDetailsFragment = new TripDetailsFragment();
        tripDetailsFragment.setArguments(arguments);
        return tripDetailsFragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        saveMenuItem = menu.findItem(R.id.action_save_trip);
        cancelMenuItem = menu.findItem(R.id.action_cancel);
        editMenuItem = menu.findItem(R.id.action_edit_trip);
        deleteMenuItem = menu.findItem(R.id.action_delete_trip);
        initControls();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_trip:
                getBinding().getPresenter().edit();
                return true;
            case R.id.action_cancel:
                getBinding().getPresenter().cancel();
                return true;
            case R.id.action_save_trip:
                getBinding().getPresenter().save();
                return true;
            case R.id.action_delete_trip:
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
    protected boolean displayHomeAsUp() {
        return true;
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.title_trip_details;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trip_details;
    }

    @Override
    protected void initBinding(FragmentTripDetailsBinding binding) {
        binding.setDecoration(new DividerItemDecoration(ContextCompat.getDrawable(getContext(), R.drawable.small_divider)));
        binding.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.setPresenter(new TripDetailsPresenter(this, getMode(), getTripId()));
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
    public void addTripEvent() {
        replaceFragment(TripEventFragment.add(getTripId()), null);
    }

    @Override
    public void viewTripEvent(long tripEventId) {
        replaceFragment(TripEventFragment.view(tripEventId), null);
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

    @SuppressWarnings("WrongConstant")
    @Mode
    private int getMode() {
        return getArguments().getInt(Key.MODE);
    }

    private long getTripId() {
        return getArguments().getLong(Key.TRIP_ID);
    }

}