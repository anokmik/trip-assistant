package com.anokmik.tripassistant.user.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.base.BaseFragment;
import com.anokmik.tripassistant.databinding.FragmentLoginBinding;

public final class LoginFragment extends BaseFragment<FragmentLoginBinding> implements LoginContract.View {

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setActionBarTitle(R.string.title_user_login);
    }

    @Override
    protected boolean displayHomeAsUp() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initBinding(FragmentLoginBinding binding) {
        binding.setPresenter(new LoginPresenter(this));
    }

    @Override
    public void back() {
        getActivity().onBackPressed();
    }

}