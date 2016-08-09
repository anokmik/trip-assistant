package com.anokmik.tripassistant.trip;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.author.AuthorActivity;
import com.anokmik.tripassistant.base.BaseActivity;
import com.anokmik.tripassistant.databinding.ActivityTripBinding;
import com.anokmik.tripassistant.trip.list.TripsListFragment;

public final class TripActivity extends BaseActivity<ActivityTripBinding> implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            addFragment(TripsListFragment.newInstance());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trip, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_trip;
    }

    @Override
    protected void initBinding(ActivityTripBinding binding) {
        setSupportActionBar(binding.toolbar);
        setContainerId(binding.container.getId());
        binding.setMenuItemClickListener(this);
        binding.setNavigationClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_author:
                launchActivity(AuthorActivity.class);
                return true;
            default:
                return false;
        }
    }

}