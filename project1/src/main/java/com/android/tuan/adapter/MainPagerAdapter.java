package com.android.tuan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.android.tuan.fragment.HomeFragment;
import com.android.tuan.fragment.HomeFragment1;
import com.android.tuan.fragment.MessageFragment;
import com.android.tuan.fragment.MineFragment;
import com.android.tuan.fragment.TypeFragment;


public class MainPagerAdapter extends FragmentPagerAdapter {
    private AppCompatActivity context;

    public MainPagerAdapter(AppCompatActivity context) {
        super(context.getSupportFragmentManager());
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeFragment1.getInstant();
            case 1:
                return TypeFragment.getInstant();
            case 2:
                return MessageFragment.getInstant();
            case 3:
                return MineFragment.getInstant();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

}
