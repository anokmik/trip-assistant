package com.anokmik.tripassistant.user.login

import android.os.Bundle

import com.anokmik.tripassistant.R
import com.anokmik.tripassistant.base.BaseFragment
import com.anokmik.tripassistant.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(), LoginContract.View {

    override val displayHomeAsUp = true

    override val layoutId = R.layout.fragment_login

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setActionBarTitle(R.string.title_user_login)
    }

    override fun initBinding(binding: FragmentLoginBinding) {
        binding.presenter = LoginPresenter(this)
    }

    override fun back() = activity.onBackPressed()

    companion object {

        fun newInstance(): LoginFragment {
            return LoginFragment()
        }

    }

}