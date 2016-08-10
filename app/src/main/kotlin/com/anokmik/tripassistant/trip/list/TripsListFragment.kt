package com.anokmik.tripassistant.trip.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.anokmik.persistence.model.Trip
import com.anokmik.tripassistant.BR
import com.anokmik.tripassistant.R
import com.anokmik.tripassistant.base.BaseFragment
import com.anokmik.tripassistant.databinding.FragmentTripsListBinding

class TripsListFragment : BaseFragment<FragmentTripsListBinding>(), TripsListContract.View {

    override val displayHomeAsUp = false

    override val layoutId = R.layout.fragment_trips_list

    override val rowItemLayoutId = R.layout.row_trip

    override val itemBindingId = BR.trip

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setActionBarTitle(R.string.title_trips_list)
    }

    override fun initBinding(binding: FragmentTripsListBinding) {
        binding.layoutManager = LinearLayoutManager(context)
        binding.tripListPresenter = TripsListPresenter(this)
    }

    override fun showTrip(trip: Trip?) {
        //TODO: Mikle, implement
    }

    companion object {

        fun newInstance(): TripsListFragment {
            return TripsListFragment()
        }

    }

}