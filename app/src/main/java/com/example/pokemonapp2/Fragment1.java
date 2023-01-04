package com.example.pokemonapp2;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
/*
public class Fragment1 extends androidx.fragment.app.Fragment {
Context d;
    View customAlertView;
    List<String> list1 = new ArrayList<String>();
int index;
    public Fragment1(int i) {
        index=i;


    }

    private FragmentActivity myContext;

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    //最初に読み込まれる
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     customAlertView = inflater.inflate(R.layout.fragment_fragment1, null);
       d= customAlertView.getContext();



       return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //viewがコンテキスト

       RecyclerView ptrecy = view.findViewById(R.id.pt_recycler_view);
        // RecyclerViewのレイアウトサイズを変更しない設定をONにする
        // パフォーマンス向上のための設定。
        ptrecy.setHasFixedSize(true);
        // RecyclerViewにlayoutManagerをセットする。
        // このlayoutManagerの種類によって「1列のリスト」なのか「２列のリスト」とかが選べる。
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(d);

        ptrecy.setLayoutManager(layoutManager);
        FragmentManager fragManager = myContext.getFragmentManager();

        // Adapter生成してRecyclerViewにセット
       RecyclerView.Adapter mainAdapter = new ptrecycle(d,ptRowData());
        ptrecy.setAdapter(mainAdapter);
        ((ptrecycle) mainAdapter).setOnItemClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(d, "oooooできた", Toast.LENGTH_SHORT).show();
            DialogFragment dialogFragment = new ptDialogFragment(d);
            dialogFragment.show( myContext.getSupportFragmentManager(), "my_dialog2");
        }
    });

    }
    public  void ptDB(){
        //db
      PTDB myOpenHelper = new PTDB(d);
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        //select
        Cursor c =  db.rawQuery("select ID1 from pokemondb",null);
        c.moveToFirst();
            String str = c.getString(0);
            list1.add(str);
        c.close();

        c =  db.rawQuery("select ID2 from pokemondb",null);
        c.moveToFirst();
         str = c.getString(0);
        list1.add(str);
        c.close();

        c =  db.rawQuery("select ID3 from pokemondb",null);
        c.moveToFirst();
        str = c.getString(0);
        list1.add(str);
        c.close();

        c =  db.rawQuery("select ID4 from pokemondb",null);
        c.moveToFirst();
        str = c.getString(0);
        list1.add(str);
        c.close();

        c =  db.rawQuery("select ID5 from pokemondb",null);
        c.moveToFirst();
        str = c.getString(0);
        list1.add(str);
        c.close();

        c =  db.rawQuery("select ID6 from pokemondb",null);
        c.moveToFirst();
        str = c.getString(0);
        list1.add(str);
        c.close();

    }




}*/