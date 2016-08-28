package com.anokmik.tripassistant.user

import android.os.Bundle
import android.view.View

import com.anokmik.persistence.model.User
import com.anokmik.tripassistant.R
import com.anokmik.tripassistant.base.BaseActivity
import com.anokmik.tripassistant.databinding.ActivityUserBinding
import com.anokmik.tripassistant.user.login.LoginFragment
import com.anokmik.tripassistant.user.profile.ProfileFragment

class UserActivity : BaseActivity<ActivityUserBinding>(), UserContract.View, View.OnClickListener {

    private val userPresenter = UserPresenter(this)

    init {
        layoutId = R.layout.activity_user
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            userPresenter.validateUser()
        }
    }

    override fun initBinding(binding: ActivityUserBinding) {
        setSupportActionBar(binding.toolbar)
        containerId = binding.container.id
        binding.navigationClickListener = this
    }

    override fun showLogin() {
        addFragment(LoginFragment.newInstance())
    }

    override fun showProfile(user: User) {
        addFragment(ProfileFragment.newInstance(user.id))
    }

    override fun onClick(view: View) = onBackPressed()

}