package com.example.myfirstapplication;

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class TabActivity extends AppCompatActivity  {

    private TabLayout layout;
    private TabAdapter adapter;
    private ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.home,
            R.drawable.notifications,
            R.drawable.profile
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout = findViewById(R.id.tabs);

        viewPager = findViewById(R.id.view_pager);

        adapter = new TabAdapter(getSupportFragmentManager(),1);

        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new NotificationFragment(), "Notification");
        adapter.addFragment(new ProfileFragment(), "Profile");

        viewPager.setAdapter(adapter);
        layout.setupWithViewPager(viewPager);
        layout.getTabAt(0).setIcon(tabIcons[0]);
        layout.getTabAt(1).setIcon(tabIcons[1]);
        layout.getTabAt(2).setIcon(tabIcons[2]);


    }

}