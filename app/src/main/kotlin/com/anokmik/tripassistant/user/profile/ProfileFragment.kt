package com.anokmik.tripassistant.user.profile

import android.os.Bundle

import com.anokmik.tripassistant.R
import com.anokmik.tripassistant.base.BaseFragment
import com.anokmik.tripassistant.databinding.FragmentProfileBinding
import com.anokmik.tripassistant.trip.USER_ID

class ProfileFragment : BaseFragment<FragmentProfileBinding>(), ProfileContract.View {

    override val displayHomeAsUp = true

    override val layoutId = R.layout.fragment_profile

    override val titleResourceId = R.string.title_profile

    private val userId by lazy { arguments.getLong(USER_ID) }

    override fun initBinding(binding: FragmentProfileBinding) {
        binding.presenter = ProfilePresenter(this, userId)
    }

    override fun back() = activity.onBackPressed()

    companion object {

        fun newInstance(userId: Long): ProfileFragment {
            val args = Bundle()
            args.putLong(USER_ID, userId)
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }

    }

}