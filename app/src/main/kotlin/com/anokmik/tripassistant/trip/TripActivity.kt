package com.anokmik.tripassistant.trip

import android.os.Bundle
import android.view.View
import com.anokmik.tripassistant.R
import com.anokmik.tripassistant.base.BaseActivity
import com.anokmik.tripassistant.databinding.ActivityTripBinding
import com.anokmik.tripassistant.trip.list.TripsListFragment

class TripActivity : BaseActivity<ActivityTripBinding>(), View.OnClickListener {

    init {
        layoutId = R.layout.activity_trip
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            addFragment(TripsListFragment.newInstance())
        }
    }

    override fun initBinding(binding: ActivityTripBinding) {
        setSupportActionBar(binding.toolbar)
        containerId = binding.container.id
        binding.navigationClickListener = this
    }

    override fun onClick(view: View) = onBackPressed()

}