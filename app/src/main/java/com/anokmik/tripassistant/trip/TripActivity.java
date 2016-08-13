package com.anokmik.tripassistant.trip;

import android.os.Bundle;
import android.view.View;

import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.base.BaseActivity;
import com.anokmik.tripassistant.databinding.ActivityTripBinding;
import com.anokmik.tripassistant.trip.list.TripsListFragment;

public final class TripActivity extends BaseActivity<ActivityTripBinding> implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            addFragment(TripsListFragment.newInstance());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_trip;
    }

    @Override
    protected void initBinding(ActivityTripBinding binding) {
        setSupportActionBar(binding.toolbar);
        setContainerId(binding.container.getId());
        binding.setNavigationClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

}