package com.anokmik.tripassistant.trip.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.anokmik.persistence.model.Trip
import com.anokmik.tripassistant.BR
import com.anokmik.tripassistant.R
import com.anokmik.tripassistant.author.AuthorActivity
import com.anokmik.tripassistant.base.BaseFragment
import com.anokmik.tripassistant.databinding.FragmentTripsListBinding
import com.anokmik.tripassistant.trip.details.TripDetailsFragment

class TripsListFragment : BaseFragment<FragmentTripsListBinding>(), TripsListContract.View {

    override val displayHomeAsUp = false

    override val layoutId = R.layout.fragment_trips_list

    override val rowItemLayoutId = R.layout.row_trip

    override val itemBindingId = BR.trip

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setActionBarTitle(R.string.title_trips_list)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_trip -> {
                replaceFragment(TripDetailsFragment.add())
                return true
            }
            R.id.action_author -> {
                launchActivity(AuthorActivity::class.java)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun getOptionMenuResourceId(): Int = R.menu.menu_trip

    override fun initBinding(binding: FragmentTripsListBinding) {
        binding.layoutManager = LinearLayoutManager(context)
        binding.tripsListPresenter = TripsListPresenter(this)
    }

    override fun showTrip(trip: Trip?) {
        replaceFragment(TripDetailsFragment.view(trip?.id))
    }

    companion object {

        fun newInstance() = TripsListFragment()

    }

}