package com.sixteenmb.abhishekint.liberty;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishekint on 06-05-2017.
 */

public class MyAdapter extends FragmentStatePagerAdapter {
    List<String> list=new ArrayList<>();
    public MyAdapter(FragmentManager fm) {

        super(fm);
        list.add("Sources");
        list.add("Top");
        list.add("Latest");
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            Fragment fragment = new Sources();
            return fragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {

            return list.get(position);

    }
}