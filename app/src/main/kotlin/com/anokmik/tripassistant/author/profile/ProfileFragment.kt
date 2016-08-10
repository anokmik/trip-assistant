package com.anokmik.tripassistant.author.profile

import android.os.Bundle
import com.anokmik.tripassistant.R
import com.anokmik.tripassistant.base.BaseFragment
import com.anokmik.tripassistant.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>(), ProfileContract.View {

    override val displayHomeAsUp = true

    override val layoutId = R.layout.fragment_profile

    private val authorId by lazy { arguments.getLong(KEY_AUTHOR_ID) }

    override fun initBinding(binding: FragmentProfileBinding) {
        binding.presenter = ProfilePresenter(this, authorId)
    }

    override fun setProfileTitle() {
        setActionBarTitle(R.string.title_profile)
    }

    override fun setEditAuthorTitle() {
        setActionBarTitle(R.string.title_edit_author)
    }

    override fun back() {
        activity.onBackPressed()
    }

    companion object {

        private val KEY_AUTHOR_ID = "key_author_id"

        fun newInstance(authorId: Long): ProfileFragment {
            val args = Bundle()
            args.putLong(KEY_AUTHOR_ID, authorId)
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }

    }

}