package com.example.pokemonapp2;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ptfrag extends androidx.fragment.app.Fragment {
    View customAlertView;
    Context d;
    List<String> list1 = new ArrayList<String>();
    private FragmentActivity myContext;
    String id;
    public ptfrag(int i) {
        id = String.valueOf(i);
    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    //最初に読み込まれる
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        customAlertView = inflater.inflate(R.layout.ptfragment, null);
        d= customAlertView.getContext();
        return inflater.inflate(R.layout.ptfragment, container, false);
    }

    //viewの設定
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //viewがコンテキスト
        RecyclerView ptrecy = view.findViewById(R.id.pt_recycler_view);
        ptOpenHelper helper2;



        // RecyclerViewのレイアウトサイズを変更しない設定をONにする
        // パフォーマンス向上のための設定。
        ptrecy.setHasFixedSize(true);

        // RecyclerViewにlayoutManagerをセットする。
        // このlayoutManagerの種類によって「1列のリスト」なのか「２列のリスト」とかが選べる。
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(d);
        ptrecy.setLayoutManager(layoutManager);
        FragmentManager fragManager = myContext.getFragmentManager();

        // Adapter生成してRecyclerViewにセット
        RecyclerView.Adapter mainAdapter = new ptrecycle(d,ptRowData(),myContext);
        ptrecy.setAdapter(mainAdapter);
    }

    private List<ptfrag.RowData> ptRowData() {
        List<ptfrag.RowData>dataSet = new ArrayList<>();
        int i;


        helper2 = new ptOpenHelper(d);
        SQLiteDatabase db = helper2.getReadableDatabase();
        String read = id;
        Cursor cursor = db.query(
                "pokemonptdb",
                new String[]{"ID","ID2","ID3","ID4","ID5","ID6"},
                "_ID = ?",
                new String[]{read},
                null, null, null
        );

        cursor.moveToFirst();

        for(i=0;i<6;i++){
            ptfrag.RowData data2 = new ptfrag.RowData();
            data2.id=id;
            data2.test = cursor.getString(i);
            dataSet.add(data2);
        }

        cursor.close();

        return  dataSet;
    }
    ptOpenHelper helper2;

    //リターン時



    class RowData{
        String test;
        String id;


    }
}
