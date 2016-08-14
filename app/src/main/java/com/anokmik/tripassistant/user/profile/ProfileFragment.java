package com.anokmik.tripassistant.user.profile;

import android.os.Bundle;

import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.base.BaseFragment;
import com.anokmik.tripassistant.databinding.FragmentProfileBinding;

public final class ProfileFragment extends BaseFragment<FragmentProfileBinding> implements ProfileContract.View {

    private static final String KEY_USER_ID = "key_user_id";

    public static ProfileFragment newInstance(long userId) {
        Bundle args = new Bundle();
        args.putLong(KEY_USER_ID, userId);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean displayHomeAsUp() {
        return true;
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
    public void setProfileTitle() {
        setActionBarTitle(R.string.title_profile);
    }

    @Override
    public void setEditUserTitle() {
        setActionBarTitle(R.string.title_edit_user);
    }

    @Override
    public void back() {
        getActivity().onBackPressed();
    }

    private long getUserId() {
        return getArguments().getLong(KEY_USER_ID);
    }

}