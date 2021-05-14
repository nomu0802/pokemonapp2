package com.example.pokemonapp2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class pokesorch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokesorch);
        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);



        //json読み込み
        json();

        // RecyclerViewのレイアウトサイズを変更しない設定をONにする
        // パフォーマンス向上のための設定。
        recyclerView.setHasFixedSize(true);

        // RecyclerViewにlayoutManagerをセットする。
        // このlayoutManagerの種類によって「1列のリスト」なのか「２列のリスト」とかが選べる。
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adapter生成してRecyclerViewにセット
        RecyclerView.Adapter mainAdapter = new MainAdapter(pokesorch.this,createRowData());
        recyclerView.setAdapter(mainAdapter);


        //検索イベント
        EditText editText = (EditText) findViewById(R.id.sorchnameedit);

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //テキスト変更前

                Toast.makeText(pokesorch.this,"test" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //テキスト変更中
                String test = editText.getText().toString();
                if(s.length()==0){


                    Toast.makeText(pokesorch.this,"test" , Toast.LENGTH_SHORT).show();

                }
                else{sorchname(test);}


                RecyclerView.Adapter mainAdapter = new MainAdapter(pokesorch.this,createRowData());
                recyclerView.setAdapter(mainAdapter);


            }

            @Override
            public void afterTextChanged(Editable s) {
     
            }
        });
    }

    private List<RowData> createRowData() {
        List<RowData> dataSet = new ArrayList<>();
        int i;
        if(keys==0) {
            i = 0;


            while (i < pokemondata.size()) {
                pokedata pkm = pokemondata.get(i);
                RowData data = new RowData();
                data.postion = pkm.id;
                data.hogeTitle = pkm.pokename;

                data.hogeContents = pkm.type1;
                data.poketype2 = pkm.type2;
                dataSet.add(data);
                i = i + 1;
            }
        }

        else{
            for(i=0;i<sorchdata.size();i++){
                String idso = sorchdata.get(i).toString();
                int num = Integer.parseInt(idso);

                pokedata pkm = pokemondata.get(num);
                RowData data = new RowData();
                data.postion = pkm.id;
                data.hogeTitle = pkm.pokename;

                data.hogeContents = pkm.type1;
                data.poketype2 = pkm.type2;
                dataSet.add(data);
            }
keys=0;

        }

        return dataSet;
    }

    //ポケモンデータ読み込み
    //データの設定
    List<pokedata> pokemondata = new ArrayList<>();
    ArrayList<String> pkmname = new ArrayList<>();


    void json(){
        String jsondata = null;
        AssetManager assetManager;
        InputStream is = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getResources().getAssets().open("pokedata.json")));
            jsondata = br.readLine();
            JSONArray datas = new JSONArray(jsondata);


            for (int i = 0; i < datas.length(); i++){
                pokedata pkm = new pokedata();
                JSONObject jdata = datas.getJSONObject(i);

                //名前
                String name = jdata.getString("name");
                pkm.pokename=name;
                pkmname.add(name);
                //NO
                String id= jdata.getString("No");
                int no = Integer.parseInt(id);
                pkm.id=no;

                //タイプ
                JSONArray typeli = jdata.getJSONArray("type");
                pkm.type2="";
                for(int u=0;u<typeli.length();u++) {
                    if(u==1){
                        pkm.type2 = typeli.getString(1);

                    }
                    else {
                        String type = typeli.getString(0);
                        pkm.type1 = type;
                    }
                }
                //リストに設定
                pokemondata.add(pkm);

            }

        }catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    ArrayList<String> sorchdata = new ArrayList<>();
    int keys=0;
    //ポケモンの検索
    void sorchname(String test){
        keys=1;
        sorchdata.clear();
        for(int i=0;i<pkmname.size();i++){
            String str = pkmname.get(i).toString();
            if(str.contains(test)){
                Integer id = Integer.valueOf(i+1);
                String strid = id.toString();
                sorchdata.add(strid);
            }
        }

    }

    public void syousaisorch(View view) {
        DialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "my_dialog2");
    }


    class RowData {
        Image hogeImage;
        String hogeTitle;
        String hogeContents;
        String poketype2;

        int postion;
    }



    class pokedata{
        String pokename;
        String type1;
        String type2;
        int id;

    }
}