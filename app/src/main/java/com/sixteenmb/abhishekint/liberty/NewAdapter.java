package com.sixteenmb.abhishekint.liberty;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishekint on 06-05-2017.
 */

public class NewAdapter extends FragmentStatePagerAdapter {
    List<String> list=new ArrayList<>();
    int i;

    /*public NewAdapter(FragmentManager fm) {

        super(fm);
    }*/

    public NewAdapter(FragmentManager fm,int i) {


        super(fm);
        list.add("Top News");
        list.add("Latest News");
        this.i=i;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            Fragment fragment = new Top();
            return fragment;
        }
        if(position==1)
        {
            Fragment fragment=new Latest();
            return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return i;
    }

    @Override
    public CharSequence getPageTitle(int position) {
            return list.get(position);

    }
}