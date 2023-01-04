package com.example.pokemonapp2;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ptpageadapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private ptOpenHelper helper;
        //コンストラクタ
    public ptpageadapter(FragmentManager manager,Context c) {
        super(manager);
        helper = new ptOpenHelper(c);
        SQLiteDatabase db = helper.getReadableDatabase();
        //カラムの数を数える
        int numRows = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM pokemonptdb", null);


        for(int i =1;i<=numRows;i++) {
            mFragmentList.add(new ptfrag(i));
        }
    }


    @NonNull
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
        return "■";
    }

}

