package com.example.userdetail;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userdetail.databinding.HomeFragmentBinding;
import com.example.userdetail.userdetails.UserDetailFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        HomeFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mViewModel.viewDetail.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                replaceFragment(user);
            }
        });
//        binding.userRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                if (layoutManager != null) {
//                    int total = layoutManager.getItemCount();
//                    int firstVisibleItemCount = layoutManager.findFirstVisibleItemPosition();
//                    int lastVisibleItemCount = layoutManager.findLastVisibleItemPosition();
//
//                    if (total > 0) {
//                        if ((total - 2) == lastVisibleItemCount) {
//                            mViewModel.userListAdapter.updateData(mViewModel.userArrayList.subList(total, total + 9));
//                        }
//                    }
//
//                }
//            }
//        });

        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

    private void replaceFragment(User user) {
        DashboardActivity activity = (DashboardActivity) getActivity();
        UserDetailFragment fragment = UserDetailFragment.newInstance(user);
        activity.loadFragment(fragment, false, "USERDETAIL");
    }


}
