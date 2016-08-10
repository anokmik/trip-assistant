package com.anokmik.tripassistant.trip

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.anokmik.tripassistant.R
import com.anokmik.tripassistant.author.AuthorActivity
import com.anokmik.tripassistant.base.BaseActivity
import com.anokmik.tripassistant.databinding.ActivityTripBinding
import com.anokmik.tripassistant.trip.list.TripsListFragment

class TripActivity : BaseActivity<ActivityTripBinding>(), View.OnClickListener, Toolbar.OnMenuItemClickListener {

    init {
        layoutId = R.layout.activity_trip
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            addFragment(TripsListFragment.newInstance())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_trip, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun initBinding(binding: ActivityTripBinding) {
        setSupportActionBar(binding.toolbar)
        containerId = binding.container.id
        binding.menuItemClickListener = this
        binding.navigationClickListener = this
    }

    override fun onClick(view: View) {
        onBackPressed()
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_author -> {
                launchActivity(AuthorActivity::class.java)
                return true
            }
            else -> return false
        }
    }

}