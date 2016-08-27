package com.anokmik.tripassistant.trip.list;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.anokmik.persistence.model.Trip;
import com.anokmik.tripassistant.BR;
import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.base.BaseFragment;
import com.anokmik.tripassistant.databinding.FragmentTripsListBinding;
import com.anokmik.tripassistant.trip.details.TripDetailsFragment;
import com.anokmik.tripassistant.user.UserActivity;
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;

public final class TripsListFragment extends BaseFragment<FragmentTripsListBinding> implements TripsListContract.View {

    public static TripsListFragment newInstance() {
        return new TripsListFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_trip:
                replaceFragment(TripDetailsFragment.add(), null);
                return true;
            case R.id.action_show_user:
                launchActivity(UserActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int getOptionMenuResourceId() {
        return R.menu.menu_trips_list;
    }

    @Override
    protected boolean displayHomeAsUp() {
        return false;
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.title_trips_list;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trips_list;
    }

    @Override
    protected void initBinding(FragmentTripsListBinding binding) {
        binding.setDecoration(new DividerItemDecoration(ContextCompat.getDrawable(getContext(), R.drawable.large_divider)));
        binding.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.setPresenter(new TripsListPresenter(this));
    }

    @Override
    public int getRowItemLayoutId() {
        return R.layout.row_trip;
    }

    @Override
    public int getItemBindingId() {
        return BR.trip;
    }

    @Override
    public void showTrip(Trip trip) {
        replaceFragment(TripDetailsFragment.view(trip.id), null);
    }

}