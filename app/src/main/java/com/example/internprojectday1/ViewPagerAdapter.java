package com.example.internprojectday1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new TodayTaskFragment();
            case 1:
                return new YesterdayTaskFragment();
            case 2:
                return new TommorowTaskFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3; }
}