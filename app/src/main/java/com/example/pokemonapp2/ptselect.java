package com.example.pokemonapp2;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.google.android.material.tabs.TabLayout;

public class ptselect extends AppCompatActivity {
    Context c;
    TabLayout tabLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptselect);

        //ViewPagerの取得
        ViewPager viewPager = (ViewPager) findViewById(R.id.ptviewpager);

        //TabLayoutを取得してそれをViewPagerにセット
        tabLayout2 = (TabLayout) findViewById(R.id.pttab_layout);
        tabLayout2.setupWithViewPager(viewPager);


       /* tabLayout2.addTab(tabLayout2.newTab().setCustomView(R.layout.ptselecticon));
        tabLayout2.addTab(tabLayout2.newTab().setCustomView(R.layout.ptselection2));
        tabLayout2.addTab(tabLayout2.newTab().setCustomView(R.layout.ptselection2));
        tabLayout2.getTabAt(0).setCustomView(R.layout.ptselecticon);


        tabLayout2.getTabAt(1).setCustomView(R.layout.ptselection2);
        tabLayout2.getTabAt(2).setCustomView(R.layout.ptselection2);*/





            c = ptselect.this;
        //Adapterを取得してそれをViewPagerにセット
        ptpageadapter viewPagerAdapter = new ptpageadapter(getSupportFragmentManager(), c);
       
        viewPager.setAdapter(viewPagerAdapter);
        helper = new ptOpenHelper(c);

    }

    public void test(View view) {
        Context c = ptselect.this;
        DialogFragment dialogFragment = new dougu(c);
        dialogFragment.show(getSupportFragmentManager(), "my_dialog2");
    }

    private ptOpenHelper helper;
    String idg;



    public void testpt(View view) {







    }


    @Override
    protected  void onRestart(){
        super.onRestart();
        reload();
    }
    public void reload(){
        Intent intent = getIntent();
        overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();;
        overridePendingTransition(0,0);
        startActivity(intent);
    }



    public void readDate(String read) {

    }

    public void dbadd(View view) {
        Context c = ptselect.this;
        DialogFragment dialogFragment = new dbadd(c);
        dialogFragment.show(getSupportFragmentManager(), "my_dialog2");

    }

    public void dbdelete(View view) {
        int tabposition = tabLayout2.getSelectedTabPosition();
        DialogFragment dialogFragment = new dbdelete(c,tabposition);
        dialogFragment.show(getSupportFragmentManager(), "my_dialog2");

    }
}