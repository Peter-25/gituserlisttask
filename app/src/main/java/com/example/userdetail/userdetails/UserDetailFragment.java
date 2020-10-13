package com.example.userdetail.userdetails;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userdetail.R;
import com.example.userdetail.User;
import com.example.userdetail.databinding.UserDetailFragmentBinding;

public class UserDetailFragment extends Fragment {

    private UserDetailViewModel mViewModel;
    static User user = new User();

    public static UserDetailFragment newInstance() {
        return new UserDetailFragment();
    }

    public static UserDetailFragment newInstance(User mUser) {
        UserDetailFragment fragment = new UserDetailFragment();
        user = mUser;
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        UserDetailFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.user_detail_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(UserDetailViewModel.class);
        binding.setMViewModel(mViewModel);
        mViewModel.setImage(user.getImage());
        binding.username.setText(user.getName());
        binding.desc.setText(user.getDescription());
        binding.repourl.setText(user.getGitUrl());
        mViewModel.getComments(user.getUserId());
        return binding.getRoot();
    }


}
