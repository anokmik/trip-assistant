package com.anokmik.tripassistant.author.profile;

import android.os.Bundle;

import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.base.BaseFragment;
import com.anokmik.tripassistant.databinding.FragmentProfileBinding;

public final class ProfileFragment extends BaseFragment<FragmentProfileBinding> implements ProfileContract.View {

    private static final String KEY_AUTHOR_ID = "key_author_id";

    public static ProfileFragment newInstance(long authorId) {
        Bundle args = new Bundle();
        args.putLong(KEY_AUTHOR_ID, authorId);
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
        binding.setPresenter(new ProfilePresenter(this, getAuthorId()));
    }

    @Override
    public void setProfileTitle() {
        setActionBarTitle(R.string.title_profile);
    }

    @Override
    public void setEditAuthorTitle() {
        setActionBarTitle(R.string.title_edit_author);
    }

    @Override
    public void back() {
        getActivity().onBackPressed();
    }

    private long getAuthorId() {
        return getArguments().getLong(KEY_AUTHOR_ID);
    }

}