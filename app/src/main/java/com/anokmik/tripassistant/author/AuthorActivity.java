package com.anokmik.tripassistant.author;

import android.os.Bundle;
import android.view.View;

import com.anokmik.persistence.model.Author;
import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.author.login.LoginFragment;
import com.anokmik.tripassistant.author.profile.ProfileFragment;
import com.anokmik.tripassistant.base.BaseActivity;
import com.anokmik.tripassistant.databinding.ActivityAuthorBinding;

public final class AuthorActivity extends BaseActivity<ActivityAuthorBinding> implements AuthorContract.View, View.OnClickListener {

    private final AuthorPresenter authorPresenter;

    public AuthorActivity() {
        this.authorPresenter = new AuthorPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            authorPresenter.validateAuthor();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_author;
    }

    @Override
    protected void initBinding(ActivityAuthorBinding binding) {
        setSupportActionBar(binding.toolbar);
        setContainerId(binding.container.getId());
        binding.setNavigationClickListener(this);
    }

    @Override
    public void showLogin() {
        addFragment(LoginFragment.newInstance());
    }

    @Override
    public void showProfile(long authorId) {
        addFragment(ProfileFragment.newInstance(authorId));
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

}