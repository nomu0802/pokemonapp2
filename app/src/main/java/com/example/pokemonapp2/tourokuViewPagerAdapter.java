package com.example.pokemonapp2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class tourokuViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public tourokuViewPagerAdapter(FragmentManager manager, ArrayList<String> syuzokuli ,ArrayList<String> wazali,ArrayList<String> wazali3,ArrayList<String> memoli) {
        super(manager);
        mFragmentList.add(new status(syuzokuli));
        mFragmentList.add(new wazaselect(wazali,wazali3));
        mFragmentList.add(new memo(memoli));
    }

     //ViewPagerの数を返す
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    //ViewPagerのフラグメントを返す
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    //タイトルを返す
    @Override
    public CharSequence getPageTitle(int position) {
        return "ページ" + (position + 1);
    }
}
