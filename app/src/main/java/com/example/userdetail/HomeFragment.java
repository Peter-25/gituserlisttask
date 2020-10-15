package com.example.userdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userdetail.databinding.HomeFragmentBinding;
import com.example.userdetail.userdetails.UserDetailFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private boolean isLoading = false;

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
        binding.userRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null) {
//                    int visibleItemCount = layoutManager.getChildCount();
//                    int totalItemCount = layoutManager.getItemCount();
//                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
//
//                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
//                            && firstVisibleItemPosition >= 0
//                            && totalItemCount >= 8) {
//                        Toast.makeText(getActivity(), "Please wait...", Toast.LENGTH_SHORT).show();
//                        loadmore(totalItemCount);
//                    }

                    int total = layoutManager.getItemCount();
                    int firstVisibleItemCount = layoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItemCount = layoutManager.findLastVisibleItemPosition();

                    if (total > 0 && !isLoading) {
                        if ((total - 1) == lastVisibleItemCount) {
                            isLoading = true;
                            Toast.makeText(getActivity(), "Please wait...", Toast.LENGTH_SHORT).show();
                            loadmore(total);
                        }
                    }
                }
            }
        });

        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

    private void loadmore(int total) {
        int addedCount = 0;
        if (total < mViewModel.userArrayList.size()) {
            for (int i = total; i <= (total + 9); i++) {
                if (i < mViewModel.userArrayList.size()) {
                    mViewModel.mUserArrayList.add(mViewModel.userArrayList.get(i));
                    addedCount++;
                }
            }
            mViewModel.userListAdapter.updateData(mViewModel.mUserArrayList);
            mViewModel.userListAdapter.notifyItemRangeInserted(total, addedCount);
            isLoading = false;
        }
    }

    private void replaceFragment(User user) {
        DashboardActivity activity = (DashboardActivity) getActivity();
        UserDetailFragment fragment = UserDetailFragment.newInstance(user);
        activity.loadFragment(fragment, false, "USERDETAIL");
    }


}
