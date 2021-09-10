package com.example.myfirstproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public  SectionsPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList)
    {
        super((fragmentManager));
        this.fragmentList = fragmentList;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        return this.fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return this.fragmentList.size();
    }
}
