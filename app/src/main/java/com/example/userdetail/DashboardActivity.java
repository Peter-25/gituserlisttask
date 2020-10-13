package com.example.userdetail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.userdetail.databinding.ActivityDashboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {
    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        binding.bottomBar.setSelectedItemId(R.id.home);
        loadFragment(new HomeFragment(), false, "Home");
        binding.bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        loadFragment(fragment, false, "Home");
                        return true;
                    case R.id.message:
                        fragment = new MessageFragment();
                        loadFragment(fragment, true, "Message");
                        return true;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        loadFragment(fragment, true, "profile");
                        return true;

                }

                return false;


            }
        });
    }

    public void loadFragment(Fragment fragment, boolean canAddtoBackStack, String mTag) {
        // load fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, mTag);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (canAddtoBackStack) transaction.addToBackStack(mTag);
        transaction.commit();
    }

}
