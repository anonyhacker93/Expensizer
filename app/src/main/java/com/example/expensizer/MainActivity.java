package com.example.expensizer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.expensizer.adapter.MainTabAdapter;
import com.example.expensizer.databinding.ActivityMainBinding;
import com.example.expensizer.fragment.HomeFragment;
import com.example.expensizer.fragment.ShowExpenseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init() {
        setupTabs();
    }

    private void setupTabs() {
        TabLayout tabLayout = binding.mainTabs;
        ViewPager viewPager = binding.mainTabsViewPager;

        MainTabAdapter tabAdapter = new MainTabAdapter(getSupportFragmentManager(), getTabFragments(), getTabTitles());
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    List<Fragment> getTabFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ShowExpenseFragment());
        return fragments;
    }

    List<String> getTabTitles() {
        List<String> titleList = new ArrayList<>();
        titleList.add(getString(R.string.home_tab));
        titleList.add(getString(R.string.show_expense_tab));
        return titleList;
    }
}
