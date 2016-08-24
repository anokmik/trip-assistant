package com.anokmik.tripassistant.user.login;

import com.anokmik.tripassistant.R;
import com.anokmik.tripassistant.base.BaseFragment;
import com.anokmik.tripassistant.databinding.FragmentLoginBinding;

public final class LoginFragment extends BaseFragment<FragmentLoginBinding> implements LoginContract.View {

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected boolean displayHomeAsUp() {
        return true;
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.title_login;
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