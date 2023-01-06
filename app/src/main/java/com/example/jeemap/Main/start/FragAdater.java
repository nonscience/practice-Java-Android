package com.example.jeemap.Main.start;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.jeemap.Main.map.MediMap;
import com.example.jeemap.Main.map.MediSelect;
import com.example.jeemap.Main.map.POI;

import java.util.ArrayList;


public class FragAdater extends FragmentPagerAdapter {
    private ArrayList<Fragment> items;
    public FragAdater(FragmentManager fm){
        super(fm);
        items = new ArrayList<Fragment>();
        items.add(new MediSelect());
        //items.add(new POI());
        //items.add(new MediMap());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
