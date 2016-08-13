package com.anokmik.tripassistant.author

import android.os.Bundle
import android.view.View

import com.anokmik.persistence.model.Author
import com.anokmik.tripassistant.R
import com.anokmik.tripassistant.author.login.LoginFragment
import com.anokmik.tripassistant.author.profile.ProfileFragment
import com.anokmik.tripassistant.base.BaseActivity
import com.anokmik.tripassistant.databinding.ActivityAuthorBinding

class AuthorActivity : BaseActivity<ActivityAuthorBinding>(), AuthorContract.View, View.OnClickListener {

    private val authorPresenter = AuthorPresenter(this)

    init {
        layoutId = R.layout.activity_author
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            authorPresenter.validateAuthor()
        }
    }

    override fun initBinding(binding: ActivityAuthorBinding) {
        setSupportActionBar(binding.toolbar)
        containerId = binding.container.id
        binding.navigationClickListener = this
    }

    override fun showLogin() {
        addFragment(LoginFragment.newInstance())
    }

    override fun showProfile(author: Author) {
        addFragment(ProfileFragment.newInstance(author.id))
    }

    override fun onClick(view: View) = onBackPressed()

}