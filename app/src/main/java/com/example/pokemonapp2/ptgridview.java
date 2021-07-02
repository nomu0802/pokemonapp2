package com.example.pokemonapp2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ptgridview extends AppCompatActivity {

    GridView gridView;
    //ポケモン名前
    String data = null;
    List<String> pokename = new ArrayList<>();
    List<String> search = new ArrayList<>();
    String[] num ;
    String[] num2 ;
    String[] num4 ;
    List<String> list1 = new ArrayList<String>();

    List<String> list2 = new ArrayList<String>();
    List<String> list3 = new ArrayList<String>();
    List<String> namelist = new ArrayList<String>();
    int [] nuim={1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokegrid);






        //adapterの準備
        //表示するカラム名

        String[] from = {"ID"};


        pokedex();
        pokemonjson();

        gridView = findViewById(R.id.gridview);



        gridAdapter2 adapter = new gridAdapter2(ptgridview.this,num,nuim);
        gridView.setAdapter(adapter);
        //リストビューをタップした時の各行のデータを取得
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positon, long id) {
                String h = list3.get(positon);
                String name = namelist.get(positon);
                String idg = list1.get(positon);
                String ins[] = {idg,name,h};
                Toast.makeText(getApplicationContext(),"you cliked"+h,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ptgridview.this, poketouroku.class);
                intent.putExtra("KEY",ins);
                startActivity(intent);

            }


        });


        gridView.setTextFilterEnabled(true);


    }





    public void pokedex(){
        //String型を要素に持つList型を生成

        //db
        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        //select
        Cursor c =  db.rawQuery("select id from pokemondb",null);

        boolean sql = c.moveToFirst();
        while (sql){
            String str = c.getString(0);
            list1.add(str);
            sql = c.moveToNext();
        }
        c.close();

        Cursor cc =  db.rawQuery("select _id from pokemondb",null);
        sql = cc.moveToFirst();
        while (sql){
            String str = cc.getString(0);
            list3.add(str);
            sql = cc.moveToNext();
        }
        cc.close();

        Cursor ccc =  db.rawQuery("select name from pokemondb",null);
        sql = ccc.moveToFirst();
        while (sql){
            String str = ccc.getString(0);
            namelist.add(str);
            sql = ccc.moveToNext();
        }
        ccc.close();




        //toArrayメソッドを用いて、List型をString型の配列に変換
        num = list1.toArray(new String[0]);



    }

    void pokemonjson (){


        String jsondata = null;
        AssetManager assetManager;
        InputStream is = null;
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(getResources().getAssets().open("test2.json")));
            jsondata = br.readLine();
            JSONArray json1 = new JSONArray(jsondata);
            for(int i=0;i<json1.length();i++){
                JSONObject json = json1.getJSONObject(i);
                pokename.add(json.getString("name"));


            }




        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
