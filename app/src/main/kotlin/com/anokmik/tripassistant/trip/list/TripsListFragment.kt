package com.anokmik.tripassistant.trip.list

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.anokmik.persistence.model.Trip
import com.anokmik.tripassistant.BR
import com.anokmik.tripassistant.R
import com.anokmik.tripassistant.base.BaseFragment
import com.anokmik.tripassistant.databinding.FragmentTripsListBinding
import com.anokmik.tripassistant.trip.details.TripDetailsFragment
import com.anokmik.tripassistant.user.UserActivity
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration

class TripsListFragment : BaseFragment<FragmentTripsListBinding>(), TripsListContract.View {

    override val displayHomeAsUp = false

    override val titleResourceId = R.string.title_trips_list

    override val layoutId = R.layout.fragment_trips_list

    override val rowItemLayoutId = R.layout.row_trip

    override val itemBindingId = BR.trip

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_trip -> {
                replaceFragment(TripDetailsFragment.add(), null)
                return true
            }
            R.id.action_show_user -> {
                launchActivity(UserActivity::class.java)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun getOptionMenuResourceId(): Int = R.menu.menu_trips_list

    override fun initBinding(binding: FragmentTripsListBinding) {
        binding.decoration = DividerItemDecoration(ContextCompat.getDrawable(context, R.drawable.large_divider))
        binding.layoutManager = LinearLayoutManager(context)
        binding.presenter = TripsListPresenter(this)
    }

    override fun showTrip(trip: Trip?) {
        trip?.let {
            replaceFragment(TripDetailsFragment.view(trip.id))
        }
    }

    companion object {

        fun newInstance() = TripsListFragment()

    }

}