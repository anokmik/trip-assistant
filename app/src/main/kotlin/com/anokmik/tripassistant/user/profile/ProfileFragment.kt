package com.anokmik.tripassistant.user.profile

import android.os.Bundle
import com.anokmik.tripassistant.R
import com.anokmik.tripassistant.base.BaseFragment
import com.anokmik.tripassistant.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>(), ProfileContract.View {

    override val displayHomeAsUp = true

    override val layoutId = R.layout.fragment_profile

    private val userId by lazy { arguments.getLong(KEY_USER_ID) }

    override fun initBinding(binding: FragmentProfileBinding) {
        binding.presenter = ProfilePresenter(this, userId)
    }

    override fun setProfileTitle() {
        setActionBarTitle(R.string.title_profile)
    }

    override fun setEditUserTitle() {
        setActionBarTitle(R.string.title_edit_user)
    }

    override fun back() = activity.onBackPressed()

    companion object {

        private val KEY_USER_ID = "key_user_id"

        fun newInstance(userId: Long): ProfileFragment {
            val args = Bundle()
            args.putLong(KEY_USER_ID, userId)
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }

    }

}