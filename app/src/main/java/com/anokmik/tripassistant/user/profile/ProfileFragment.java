package com.anokmik.tripassistant.user.profile;

import android.os.Bundle;

import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.base.BaseFragment;
import com.anokmik.tripassistant.databinding.FragmentProfileBinding;
import com.anokmik.tripassistant.trip.Key;

public final class ProfileFragment extends BaseFragment<FragmentProfileBinding> implements ProfileContract.View {

    public static ProfileFragment newInstance(long userId) {
        Bundle args = new Bundle();
        args.putLong(Key.USER_ID, userId);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean displayHomeAsUp() {
        return true;
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.title_profile;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initBinding(FragmentProfileBinding binding) {
        binding.setPresenter(new ProfilePresenter(this, getUserId()));
    }

    @Override
    public void back() {
        getActivity().onBackPressed();
    }

    private long getUserId() {
        return getArguments().getLong(Key.USER_ID);
    }

}