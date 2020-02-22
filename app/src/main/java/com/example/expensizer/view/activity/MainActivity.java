package com.example.expensizer.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.expensizer.R;
import com.example.expensizer.adapter.MainTabAdapter;
import com.example.expensizer.databinding.ActivityMainBinding;
import com.example.expensizer.fragment.HomeFragment;
import com.example.expensizer.fragment.ShowExpenseFragment;
import com.example.expensizer.viewmodel.MainActivityViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        setupTabs();
        setupDrawer();
        setupAddFab();
    }

    private void setupAddFab() {
        binding.addExpenseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddExpensesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupTabs() {
        TabLayout tabLayout = binding.mainTabs;
        ViewPager viewPager = binding.mainTabsViewPager;

        MainTabAdapter tabAdapter = new MainTabAdapter(getSupportFragmentManager(), getTabFragments(), viewModel.getTabTitles());
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupDrawer() {
        NavigationView navigationView = binding.navigationView;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                if (id == R.id.addCategory) {
                    Intent intent = new Intent(getApplicationContext(), AddCategoryActivity.class);
                    startActivity(intent);
                } else if (id == R.id.updateExpenses) {
                    Toast.makeText(MainActivity.this, "About Clicked", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


    }

    List<Fragment> getTabFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ShowExpenseFragment());
        return fragments;
    }


}
