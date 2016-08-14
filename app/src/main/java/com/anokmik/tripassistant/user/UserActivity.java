package com.anokmik.tripassistant.user;

import android.os.Bundle;
import android.view.View;

import com.anokmik.persistence.model.User;
import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.databinding.ActivityUserBinding;
import com.anokmik.tripassistant.user.login.LoginFragment;
import com.anokmik.tripassistant.user.profile.ProfileFragment;
import com.anokmik.tripassistant.base.BaseActivity;

public final class UserActivity extends BaseActivity<ActivityUserBinding> implements UserContract.View, View.OnClickListener {

    private final UserPresenter userPresenter;

    public UserActivity() {
        this.userPresenter = new UserPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            userPresenter.validateUser();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initBinding(ActivityUserBinding binding) {
        setSupportActionBar(binding.toolbar);
        setContainerId(binding.container.getId());
        binding.setNavigationClickListener(this);
    }

    @Override
    public void showLogin() {
        addFragment(LoginFragment.newInstance());
    }

    @Override
    public void showProfile(User user) {
        addFragment(ProfileFragment.newInstance(user.id));
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

}