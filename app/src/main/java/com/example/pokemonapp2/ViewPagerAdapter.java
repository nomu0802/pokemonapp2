package com.example.pokemonapp2;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();

    //コンストラクタ
    public ViewPagerAdapter(FragmentManager manager,Context c) {
        super(manager);


for(int i=0;i<10;i++){
    mFragmentList.add(new ptfrag(i));}




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
        return "■" ;
    }

}
