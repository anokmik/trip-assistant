package com.anokmik.tripassistant.trip.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.anokmik.persistence.model.Trip;
import com.anokmik.tripassistant.BR;
import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.author.AuthorActivity;
import com.anokmik.tripassistant.base.BaseFragment;
import com.anokmik.tripassistant.databinding.FragmentTripsListBinding;
import com.anokmik.tripassistant.trip.details.TripDetailsFragment;

public final class TripsListFragment extends BaseFragment<FragmentTripsListBinding> implements TripsListContract.View {

    public static TripsListFragment newInstance() {
        return new TripsListFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setActionBarTitle(R.string.title_trips_list);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_trip:
                replaceFragment(TripDetailsFragment.add(), null);
                return true;
            case R.id.action_author:
                launchActivity(AuthorActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int getOptionMenuResourceId() {
        return R.menu.menu_trip;
    }

    @Override
    protected boolean displayHomeAsUp() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trips_list;
    }

    @Override
    protected void initBinding(FragmentTripsListBinding binding) {
        binding.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.setTripsListPresenter(new TripsListPresenter(this));
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
        TripDetailsFragment.view(trip.id);
    }

}