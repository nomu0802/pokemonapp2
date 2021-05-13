package com.example.pokemonapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.ContentValues;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static android.graphics.Color.parseColor;

public class poketouroku extends AppCompatActivity {
    private MyOpenHelper helper;
    int index;
    String seikakus = "さみしがり";
    double seat=1;
    double sedf=1;
    double sespat=1;
    double sespdf=1;
    double sespdd=1;
    double syu;
    double kotai;
    double doryoku;
    double level;
    double nouryoku;
    String name;
    String type1;
    String type2;
    double seikakuti;
    ArrayList<String> tokuseili = new ArrayList<>();
    String no;
    String key="";
    ArrayList<String> levellist = new ArrayList<String>();
    ArrayList<String> typelist = new ArrayList<String>();
    ArrayList<String> seikaku = new ArrayList<String>();
    ArrayList<String> formli = new ArrayList<>();
    ArrayList<String> wazali = new ArrayList<>();
    ArrayList<String> syuzokuli = new ArrayList<>();
    ArrayList<String> wazalist2 = new ArrayList<String>();
    ArrayList<String> kotaiti = new ArrayList<String>();
    ArrayList<String> doryokuti = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poketouroku);
        //DB作成
        helper = new MyOpenHelper(getApplicationContext());

        //データの受け取り
        Intent intent = getIntent();
        String idex[] = intent.getStringArrayExtra("KEY");
        String id = idex[0];
        index = Integer.parseInt(id);
        String name = idex[1];
        key = idex[2];
        Drawable btnob = ResourcesCompat.getDrawable(getResources(),R.drawable.wakuon,null);

        spineradd();
        json();
    //名前のセット
        TextView na = findViewById(R.id.name);
        na.setText(name);

     //No
        TextView idno = findViewById(R.id.no1);
        idno.setText(no);

     //レベル
        ArrayAdapter<String> levelad = new ArrayAdapter<String>(this,R.layout.level_item,levellist);
        levelad.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        Spinner levelspp = (Spinner) findViewById(R.id.levelsp);
        levelspp.setAdapter(levelad);
        levelspp.setSelection(49);


     //性格
        ArrayAdapter<String> seiadapter = new ArrayAdapter<String>(this,R.layout.seikaku_item, seikaku);
        seiadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner seispinner = (Spinner) findViewById(R.id.seikaku);
        seispinner.setAdapter(seiadapter);

        //タイプ
        type();

    //特性
        ArrayAdapter<String> tokusei = new ArrayAdapter<String>(this,R.layout.seikaku_item, tokuseili);
        tokusei.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner tokuseisp = (Spinner) findViewById(R.id.tokusei);
        tokuseisp.setAdapter(tokusei);

     //フォルム
        ArrayAdapter<String> forma = new ArrayAdapter<String>(this,R.layout.forma_item, formli);
        forma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner formsp = (Spinner) findViewById(R.id.forumspin);
        formsp.setAdapter(forma);

     //種族値
         TextView hp1 = findViewById(R.id.hp1);
         hp1.setText(syuzokuli.get(0));
         TextView at1 = findViewById(R.id.at1);
         at1.setText(syuzokuli.get(1));
         TextView df1 = findViewById(R.id.df1);
         df1.setText(syuzokuli.get(2));
         TextView spat1 = findViewById(R.id.spat1);
         spat1.setText(syuzokuli.get(3));
         TextView spdf1 = findViewById(R.id.spdf1);
         spdf1.setText(syuzokuli.get(4));
         TextView spd1 = findViewById(R.id.spdd1);
         spd1.setText(syuzokuli.get(5));

     //個体値
        //個体値
        ArrayAdapter<String> kotaitiad= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, kotaiti);


        // ドロップダウンのレイアウトを指定
        kotaitiad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // ListViewにAdapterを関連付ける
        Spinner hp1sp = (Spinner) findViewById(R.id.hp2);
        hp1sp.setAdapter(kotaitiad);
        hp1sp.setSelection(30);
        Spinner at1sp = (Spinner) findViewById(R.id.at2);
        at1sp.setAdapter(kotaitiad);
        at1sp.setSelection(30);
        Spinner df1sp = (Spinner) findViewById(R.id.df2);
        df1sp.setAdapter(kotaitiad);
        df1sp.setSelection(30);
        Spinner spat1sp = (Spinner) findViewById(R.id.spat2);
        spat1sp.setAdapter(kotaitiad);
        spat1sp.setSelection(30);
        Spinner spdf1sp = (Spinner) findViewById(R.id.spdf2);
        spdf1sp.setAdapter(kotaitiad);
        spdf1sp.setSelection(30);
        Spinner spdd1sp = (Spinner) findViewById(R.id.spdd2);
        spdd1sp.setAdapter(kotaitiad);
        spdd1sp.setSelection(30);

     //努力値
        ArrayAdapter<String> doryokutiad= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, doryokuti);
        Spinner hp2sp = (Spinner) findViewById(R.id.hp3);
        hp2sp.setAdapter(doryokutiad);
        Spinner at2sp = findViewById(R.id.at3);
        at2sp.setAdapter(doryokutiad);
        Spinner df2sp = (Spinner) findViewById(R.id.df3);
        df2sp.setAdapter(doryokutiad);
        Spinner spat2sp = (Spinner) findViewById(R.id.spat3);
        spat2sp.setAdapter(doryokutiad);
        Spinner spdf2sp = (Spinner) findViewById(R.id.spdf3);
        spdf2sp.setAdapter(doryokutiad);
        Spinner spdd2sp = (Spinner) findViewById(R.id.spdd3);
        spdd2sp.setAdapter(doryokutiad);


    //画像のセット
        //小さいほう
        ImageView spm = findViewById(R.id.sprites);
        Bitmap image =getBitmapFromAsset("sprites/"+index);
        spm.setImageBitmap(image);
        //大きいほう
        ImageView thum = findViewById(R.id.imageView);
        Bitmap image2 =getBitmapFromAsset("thumbnails/"+index);
        thum.setImageBitmap(image2);

    //技リスト
        ArrayAdapter<String> wazaad= new ArrayAdapter<String>(this,R.layout.waza_item, wazali);
        wazaad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner waza1 = (Spinner) findViewById(R.id.waza1);
        waza1.setAdapter(wazaad);
        Spinner waza2 = (Spinner) findViewById(R.id.waza2);
        waza2.setAdapter(wazaad);
        Spinner waza3 = (Spinner) findViewById(R.id.waza3);
        waza3.setAdapter(wazaad);
        Spinner waza4 = (Spinner) findViewById(R.id.waza4);
        waza4.setAdapter(wazaad);

        Button statusbutton = findViewById(R.id.statusbuton);
        Button wazabutton = findViewById(R.id.wazabuton);
        Button memobutton = findViewById(R.id.memobuton);
        TextView butontext1 =findViewById(R.id.sttext);
        TextView butontext2 =findViewById(R.id.wazatext);
        TextView butontext3 =findViewById(R.id.memotext);
        EditText memoedit = findViewById(R.id.memoedit);
        ImageView back = findViewById(R.id.imageView2);


        if(key.length()!=0){
            Button buton = findViewById(R.id.button2);
            buton.setText("修正");
            readDate(key);

        }

        //能力初期計算
        nouryokusyu();

        statusbutton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Button statusbutton = findViewById(R.id.statusbuton);
                Button wazabutton = findViewById(R.id.wazabuton);
                Button memobutton = findViewById(R.id.memobuton);
                TextView butontext1 =findViewById(R.id.sttext);
                TextView butontext2 =findViewById(R.id.wazatext);
                TextView butontext3 =findViewById(R.id.memotext);
                EditText memoedit = findViewById(R.id.memoedit);
                ImageView back = findViewById(R.id.imageView2);


                memoedit.setEnabled(false);

                butontext1.setTextColor(parseColor("#FF5A5A"));
                butontext2.setTextColor(parseColor("#6E6E6E"));
                butontext3.setTextColor(parseColor("#6E6E6E"));
                findViewById(R.id.stwaku).bringToFront();
                back.bringToFront();


                findViewById(R.id.statutswaku1).bringToFront();
                findViewById(R.id.statutswaku2).bringToFront();
                findViewById(R.id.statustext1).bringToFront();
                findViewById(R.id.statustext2).bringToFront();
                findViewById(R.id.statustext3).bringToFront();
                findViewById(R.id.statustext4).bringToFront();


                //hp表示
                findViewById(R.id.statutshpwaku1).bringToFront();
                findViewById(R.id.statutshpwaku2).bringToFront();
                findViewById(R.id.statutshpwaku1text).bringToFront();
                findViewById(R.id.hp1waku).bringToFront();
                findViewById(R.id.hp1).bringToFront();
                findViewById(R.id.hp2waku).bringToFront();
                findViewById(R.id.hp2).bringToFront();
                findViewById(R.id.hp3waku).bringToFront();
                findViewById(R.id.hp3).bringToFront();
                findViewById(R.id.hp4waku).bringToFront();
                findViewById(R.id.hp4).bringToFront();



                //at表示
                findViewById(R.id.statutsatwaku1).bringToFront();
                findViewById(R.id.statutsatwaku2).bringToFront();
                findViewById(R.id.statutsatwaku1text).bringToFront();
                findViewById(R.id.at1waku).bringToFront();
                findViewById(R.id.at1).bringToFront();
                findViewById(R.id.at2waku).bringToFront();
                findViewById(R.id.at2).bringToFront();
                findViewById(R.id.at3waku).bringToFront();
                findViewById(R.id.at3).bringToFront();
                findViewById(R.id.at4waku).bringToFront();
                findViewById(R.id.at4).bringToFront();


                //df表示
                findViewById(R.id.statutsdfwaku1).bringToFront();
                findViewById(R.id.statutsdfwaku2).bringToFront();
                findViewById(R.id.statutsdfwaku1text).bringToFront();
                findViewById(R.id.df1waku).bringToFront();
                findViewById(R.id.df1).bringToFront();
                findViewById(R.id.df2waku).bringToFront();
                findViewById(R.id.df2).bringToFront();
                findViewById(R.id.df3waku).bringToFront();
                findViewById(R.id.df3).bringToFront();
                findViewById(R.id.df4waku).bringToFront();
                findViewById(R.id.df4).bringToFront();



                //spat表示
                findViewById(R.id.statutsspatwaku1).bringToFront();
                findViewById(R.id.statutsspatwaku2).bringToFront();
                findViewById(R.id.statutsspatwaku1text).bringToFront();
                findViewById(R.id.spat1waku).bringToFront();
                findViewById(R.id.spat1).bringToFront();
                findViewById(R.id.spat2waku).bringToFront();
                findViewById(R.id.spat2).bringToFront();
                findViewById(R.id.spat3waku).bringToFront();
                findViewById(R.id.spat3).bringToFront();
                findViewById(R.id.spat4waku).bringToFront();
                findViewById(R.id.spat4).bringToFront();



                //spdf表示
                findViewById(R.id.statutsspdfwaku1).bringToFront();
                findViewById(R.id.statutsspdfwaku2).bringToFront();
                findViewById(R.id.statutsspdfwaku1text).bringToFront();
                findViewById(R.id.spdf1waku).bringToFront();
                findViewById(R.id.spdf1).bringToFront();
                findViewById(R.id.spdf2waku).bringToFront();
                findViewById(R.id.spdf2).bringToFront();
                findViewById(R.id.spdf3waku).bringToFront();
                findViewById(R.id.spdf3).bringToFront();
                findViewById(R.id.spdf4waku).bringToFront();
                findViewById(R.id.spdf4).bringToFront();


                //spdd表示
                findViewById(R.id.statutsspddwaku1).bringToFront();
                findViewById(R.id.statutsspddwaku2).bringToFront();
                findViewById(R.id.statutsspddwaku1text).bringToFront();
                findViewById(R.id.spdd1waku).bringToFront();
                findViewById(R.id.spdd1).bringToFront();
                findViewById(R.id.spdd2waku).bringToFront();
                findViewById(R.id.spdd2).bringToFront();
                findViewById(R.id.spdd3waku).bringToFront();
                findViewById(R.id.spdd3).bringToFront();
                findViewById(R.id.spdd4waku).bringToFront();
                findViewById(R.id.spdd4).bringToFront();

                findViewById(R.id.waza1).setClickable(false);
                findViewById(R.id.waza2).setClickable(false);
                findViewById(R.id.waza3).setClickable(false);
                findViewById(R.id.waza4).setClickable(false);
                findViewById(R.id.memoedit).setClickable(false);
            }
        });


        wazabutton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                memoedit.setEnabled(false);
                //文字色
                butontext1.setTextColor(parseColor("#6E6E6E"));
                butontext2.setTextColor(parseColor("#FF5A5A"));
                butontext3.setTextColor(parseColor("#6E6E6E"));


                findViewById(R.id.wazawaku).bringToFront();
                back.bringToFront();
                findViewById(R.id.waza1text).bringToFront();
                findViewById(R.id.waza1).bringToFront();
                findViewById(R.id.waza2text).bringToFront();
                findViewById(R.id.waza2).bringToFront();
                findViewById(R.id.waza3text).bringToFront();
                findViewById(R.id.waza3).bringToFront();
                findViewById(R.id.waza4text).bringToFront();
                findViewById(R.id.waza4).bringToFront();

            }
        });




        memobutton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                memoedit.setEnabled(true);
                memoedit.setFocusable(true);
                //文字色
                butontext1.setTextColor(parseColor("#6E6E6E"));
                butontext2.setTextColor(parseColor("#6E6E6E"));
                butontext3.setTextColor(parseColor("#FF5A5A"));


                findViewById(R.id.memowaku).bringToFront();
                back.bringToFront();
                findViewById(R.id.memoedit).bringToFront();


            }
        });




        //能力値変更
        seispinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});




        levelspp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});




        hp1sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});


        hp2sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});
        at1sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});
        at2sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});
        df1sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});
        df2sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});
        spat1sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});
        spat2sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});
        spdf1sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});
        spdf2sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});
        spdd1sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});
        spdd2sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //何も選択されなかった時の動作
            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                nouryokusyu();

            }});

    }



    //検索画面にもどす
    public void serch(View view) {
        finish();
    }


    void dougjson() {
        String jsondata = null;
        AssetManager assetManager;
        InputStream is = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getResources().getAssets().open("pokedata.json")));
            jsondata = br.readLine();
            JSONArray datas = new JSONArray(jsondata);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    void json() {
    String jsondata = null;
    AssetManager assetManager;
    InputStream is = null;
    try {
        BufferedReader br = new BufferedReader(new InputStreamReader(getResources().getAssets().open("pokedata.json")));
        jsondata = br.readLine();
        JSONArray datas = new JSONArray(jsondata);
        JSONObject jdata = datas.getJSONObject(index-1);
        int i;

        //No.
        no = jdata.getString("No");

        //タイプ
        //タイプ
        JSONArray type1 = jdata.getJSONArray("type");

        for (i = 0; i < type1.length(); i++) {

            String str = (String) type1.get(i);
            typelist.add(str);

        }


        //とくせい
        JSONArray tokusei = jdata.getJSONArray("tokusei");
        for(i =0; i<tokusei.length();i++){
            tokuseili.add(tokusei.getString(i));
        }
        //種族値

        JSONArray syuzoku = jdata.getJSONArray("syuzokuti");
        for(i =0; i<syuzoku.length();i++){
            syuzokuli.add(syuzoku.getString(i));
        }

        //フォーム
        JSONArray form = jdata.getJSONArray("Form");
        for(i =0; i<form.length();i++){
            formli.add(form.getString(i));
        }
        //技
        //レベル技
        JSONArray waza = jdata.getJSONArray("levelwaza");

        for ( i = 0; i < waza.length(); i++) {
            JSONArray waza1 = waza.getJSONArray(i);
            String str = (String) waza1.get(0);
            String wazabf = (String)waza1.get(1);
            String[] wazameis = wazabf.split("New");
            String wazamei = wazameis[0];
            String wazar = str + ":" + wazamei;
            wazali.add(wazar);

        }
        JSONArray wazamachin = jdata.getJSONArray("wazamachine");

        for (i = 0; i < wazamachin.length(); i++) {
            JSONArray waza1 = wazamachin.getJSONArray(i);
            String str = (String) waza1.get(0);
            String wazabf = (String)waza1.get(1);
            String[] wazameis = wazabf.split("New");
            String wazamei = wazameis[0];
            String wazar = str + ":" + wazamei;
            wazali.add(wazar);
        }
        //タマゴ技
        JSONArray eggwazaj = jdata.getJSONArray("eggwaza");
        for (i = 0; i < eggwazaj.length(); i++) {
            JSONArray waza1 = eggwazaj.getJSONArray(i);
            String str = (String) waza1.get(0);
            String wazabf = (String)waza1.get(1);
            String[] wazameis = wazabf.split("New");
            String wazamei = wazameis[0];
            String[] wazameis2 = wazamei.split("\\[");
            wazamei = wazameis2[0];
            String wazar = str + ":" + wazamei;
            wazali.add(wazar);
        }

        //その他
        JSONArray otherj = jdata.getJSONArray("other");
        for (i = 0; i < otherj.length(); i++) {
            JSONArray waza1 = otherj.getJSONArray(i);
            String str = (String) waza1.get(0);
            String wazabf = (String)waza1.get(1);
            String[] wazameis = wazabf.split("New");
            String wazamei = wazameis[0];
            String[] wazameis2 = wazamei.split("\\[");
            wazamei = wazameis2[0];
            String wazar = str + ":" + wazamei;
            wazali.add(wazar);
        }
        //フォルム

        wazalist2.addAll(wazali.stream().distinct().collect(Collectors.toList()));


    } catch (JSONException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    //画像の読み込み
    private Bitmap getBitmapFromAsset(String id) {
        AssetManager assetManager = this.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(id+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }


    void seikakukei(){
        Spinner seik = findViewById(R.id.seikaku);
        seikakus=seik.getSelectedItem().toString();

        seat=1;
        sedf=1;
        sespdf=1;
        sespat=1;
        sespdd=1;

        if(seikakus.equals("さみしがり")){
            seat=1.1;
            sedf=0.9;


        }
        else if(seikakus.equals("いじっぱり")){

            seat=1.1;
            sespat=0.9;

        }
        else if(seikakus.equals("やんちゃ")){
            seat =1.1;
            sespdf=0.9;

        }
        else if(seikakus.equals("ゆうかん")){

            seat=1.1;
            sespdd=0.9;
        }
        else if(seikakus.equals("ずぶとい")){
            seat =0.9;
            sedf=1.1;


        }
        else if(seikakus.equals("わんぱく")){
            sedf=1.1;
            sespat =0.9;}
        else if(seikakus.equals("のうてんき")){
            sedf=1.1;
            sespdd=0.9;
        }
        else if(seikakus.equals("のんき")){
            sedf=1.1;
            sespdd=0.9;
        }
        else if(seikakus.equals("ひかえめ")){
            seat=0.9;
            sespat=1.1;
        }
        else if(seikakus.equals("おっとり")){
            sedf=0.9;
            sespat=1.1;
        }
        else if(seikakus.equals("うっかりや")){
            sespat=1.1;
            sespdf=0.9;
        }
        else if(seikakus.equals("れいせい")){
            sespat=1.1;
            sespdd=0.9;
        }
        else if(seikakus.equals("おだやか")){
            seat=0.9;
            sespdd=1.1;
        }
        else if(seikakus.equals("おとなしい")){
            sedf=0.9;
            sespdf=1.1;

        }
        else if(seikakus.equals("しんちょう")){
            sespat=0.9;
            sespdf=1.1;

        }
        else if(seikakus.equals("なまいき")){
            sespdf=1.1;
            sespdd=0.9;

        }
        else if(seikakus.equals("おくびょう")){
            seat=0.9;
            sespdd=1.1;
        }
        else if(seikakus.equals("せっかち")){
            sedf=0.9;
            sespdd=1.1;
        }
        else if(seikakus.equals("ようき")){
            sespat=0.9;
            sespdd=1.1;
        }
        else if(seikakus.equals("むじゃき")){
            sespdf=0.9;
            sespdd=1.1;
        }


        else if(seikakus.equals("てれや")){}
        else if(seikakus.equals("がんばりや")){}
        else if(seikakus.equals("すなお")){}
        else if(seikakus.equals("きまぐれ")){}
        else if(seikakus.equals("まじめ")){}






    }
    void spineradd(){

        seikaku.add("さみしがり");
        seikaku.add("いじっぱり");
        seikaku.add("やんちゃ");
        seikaku.add("ゆうかん");
        seikaku.add("ずぶとい");
        seikaku.add("わんぱく");
        seikaku.add("のうてんき");
        seikaku.add("のんき");
        seikaku.add("ひかえめ");
        seikaku.add("おっとり");
        seikaku.add("うっかりや");
        seikaku.add("れいせい");
        seikaku.add("おだやか");
        seikaku.add("おとなしい");
        seikaku.add("しんちょう");
        seikaku.add("なまいき");
        seikaku.add("おくびょう");
        seikaku.add("せっかち");
        seikaku.add("ようき");
        seikaku.add("むじゃき");
        seikaku.add("てれや");
        seikaku.add("がんばりや");
        seikaku.add("すなお");
        seikaku.add("きまぐれ");
        seikaku.add("まじめ");

        int data;
        Integer data1;
        String str;
        for(data=1;data<=31;data++){
            data1  = Integer.valueOf(data);
            str = data1.toString();
            kotaiti.add(str);



        }

        for(data=0;data<=255;data++){
            data1  = Integer.valueOf(data);
            str = data1.toString();
            doryokuti.add(str);



        }
        for(data=1;data<=100;data++){
            data1  = Integer.valueOf(data);
            str = data1.toString();
            levellist.add(str);



        }
    }

    void nouryokusyu(){
        nouryoku=0;


        //計算
        seikakukei();
        //HP
        TextView syuzoku = findViewById(R.id.hp1);
        String syuS = (String) syuzoku.getText();
        syu = Integer.parseInt(syuS);
        Spinner kotaiT = findViewById(R.id.hp2);
        String kotaiS = (String) kotaiT.getSelectedItem().toString();
        kotai = Integer.parseInt(kotaiS);
        Spinner doryokuT = findViewById(R.id.hp3);
        String doryokuS = (String) doryokuT.getSelectedItem().toString();
        doryoku = Integer.parseInt(doryokuS);
        Spinner levelT = findViewById(R.id.levelsp);
        String levelS = (String) levelT.getSelectedItem().toString();
        level = Integer.parseInt(levelS);
        hpkeisan();
        Integer i = Integer.valueOf((int) nouryoku);
        String hpnouryoku = i.toString();
        TextView nou=findViewById(R.id.hp4);
        nou.setText(hpnouryoku);



        //AT
        seikakuti =seat;
        syuzoku = findViewById(R.id.at1);
        syuS = (String) syuzoku.getText();
        syu = Integer.parseInt(syuS);
        kotaiT = findViewById(R.id.at2);
        kotaiS = (String) kotaiT.getSelectedItem().toString();
        kotai = Integer.parseInt(kotaiS);
        doryokuT = findViewById(R.id.at3);
        doryokuS = (String) doryokuT.getSelectedItem().toString();
        doryoku = Integer.parseInt(doryokuS);
        keisan();
        i = Integer.valueOf((int) nouryoku);
        hpnouryoku = i.toString();
        nou=findViewById(R.id.at4);
        nou.setText(hpnouryoku);

        //df
        seikakuti =sedf;
        syuzoku = findViewById(R.id.df1);
        syuS = (String) syuzoku.getText();
        syu = Integer.parseInt(syuS);
        kotaiT = findViewById(R.id.df2);
        kotaiS = (String) kotaiT.getSelectedItem().toString();
        kotai = Integer.parseInt(kotaiS);
        doryokuT = findViewById(R.id.df3);
        doryokuS = (String) doryokuT.getSelectedItem().toString();
        doryoku = Integer.parseInt(doryokuS);
        keisan();
        i = Integer.valueOf((int) nouryoku);
        hpnouryoku = i.toString();
        nou=findViewById(R.id.df4);
        nou.setText(hpnouryoku);

        //spat
        seikakuti =sespat;
        syuzoku = findViewById(R.id.spat1);
        syuS = (String) syuzoku.getText();
        syu = Integer.parseInt(syuS);
        kotaiT = findViewById(R.id.spat2);
        kotaiS = (String) kotaiT.getSelectedItem().toString();
        kotai = Integer.parseInt(kotaiS);
        doryokuT = findViewById(R.id.spat3);
        doryokuS = (String) doryokuT.getSelectedItem().toString();
        doryoku = Integer.parseInt(doryokuS);
        keisan();
        i = Integer.valueOf((int) nouryoku);
        hpnouryoku = i.toString();
        nou=findViewById(R.id.spat4);
        nou.setText(hpnouryoku);

        //spdf
        seikakuti =sespdf;
        syuzoku = findViewById(R.id.spdf1);
        syuS = (String) syuzoku.getText();
        syu = Integer.parseInt(syuS);
        kotaiT = findViewById(R.id.spdf2);
        kotaiS = (String) kotaiT.getSelectedItem().toString();
        kotai = Integer.parseInt(kotaiS);
        doryokuT = findViewById(R.id.spdf3);
        doryokuS = (String) doryokuT.getSelectedItem().toString();
        doryoku = Integer.parseInt(doryokuS);
        keisan();
        i = Integer.valueOf((int) nouryoku);
        hpnouryoku = i.toString();
        nou=findViewById(R.id.spdf4);
        nou.setText(hpnouryoku);

        //spdd
        seikakuti =sespdd;
        syuzoku = findViewById(R.id.spdd1);
        syuS = (String) syuzoku.getText();
        syu = Integer.parseInt(syuS);
        kotaiT = findViewById(R.id.spdd2);
        kotaiS = (String) kotaiT.getSelectedItem().toString();
        kotai = Integer.parseInt(kotaiS);
        doryokuT = findViewById(R.id.spdd3);
        doryokuS = (String) doryokuT.getSelectedItem().toString();
        doryoku = Integer.parseInt(doryokuS);
        keisan();
        i = Integer.valueOf((int) nouryoku);
        hpnouryoku = i.toString();
        nou=findViewById(R.id.spdd4);
        nou.setText(hpnouryoku);




    }
    void hpkeisan(){
        //HP
        nouryoku=(syu*2+kotai+doryoku/4)*level/100+level+10;
    }

    void keisan(){
        //HP以外計算
        nouryoku=(syu*2+kotai+doryoku/4)*level/100+5;
        nouryoku = nouryoku*seikakuti;
    }
    void type(){






        //タイプ
        Drawable type1 = ResourcesCompat.getDrawable(getResources(),R.drawable.type1,null);
        Drawable type2 = ResourcesCompat.getDrawable(getResources(),R.drawable.type2,null);
        TextView typevv = findViewById(R.id.typevi);
        TextView typename = findViewById(R.id.type1);
        TextView typevv2 = findViewById(R.id.typevi2);
        TextView typename2 = findViewById(R.id.type2);
        //タイプ

        typename.setText(typelist.get(0));

        typewild satosi = new typewild();
        String color = satosi.typoe(typelist.get(0));
        type1.setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN);
        typevv.setBackground(type1);
        typename.setText(typelist.get(0));


        String color2= "";
        typelist.add(color2);
        color2 = satosi.typoe(typelist.get(1));

        type2.setColorFilter(Color.parseColor(color2), PorterDuff.Mode.SRC_IN);
        typevv2.setBackground(type2);
        typename2.setText(typelist.get(1));

    }

    public void saveData(View view) {
        Button tourokumei = findViewById(R.id.button2);
        String nameb = tourokumei.getText().toString();

        if(nameb.equals("登録")) {

            SQLiteDatabase db = helper.getWritableDatabase();

            //名前
            TextView nadata = findViewById(R.id.name);
            String name = nadata.getText().toString();

            //ID
            TextView noda = findViewById(R.id.no1);
            String noid = noda.getText().toString();


            //特性
            Spinner tokuda = (Spinner) findViewById(R.id.tokusei);

            int idg = tokuda.getSelectedItemPosition();
            Integer i = Integer.valueOf(idg);
            String tokusei = i.toString();

            //レベル
            Spinner levelda = (Spinner) findViewById(R.id.levelsp);
            idg = levelda.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String level = i.toString();

            //HP個体値
            Spinner hpkotaitida = (Spinner) findViewById(R.id.hp2);
            idg = hpkotaitida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String hpkotaiti = i.toString();

            //AT個体値
            Spinner ATkotaitida = (Spinner) findViewById(R.id.at2);
            idg = ATkotaitida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String atkotaiti = i.toString();

            //df個体値
            Spinner dfkotaitida = (Spinner) findViewById(R.id.df2);
            idg = dfkotaitida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String dfkotaiti = i.toString();

            //spat個体値
            Spinner spatkotaitida = (Spinner) findViewById(R.id.spat2);
            idg = spatkotaitida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String spatkotaiti = i.toString();

            //spdf個体値
            Spinner spdfkotaitida = (Spinner) findViewById(R.id.spdf2);
            idg = spdfkotaitida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String spdfkotaiti = i.toString();

            //spd個体値
            Spinner spdkotaitida = (Spinner) findViewById(R.id.spdd2);
            idg = spdfkotaitida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String spdkotaiti = i.toString();

            //HP努力値
            Spinner hpdoryokutida = (Spinner) findViewById(R.id.hp3);
            idg = hpdoryokutida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String hpdoryokuti = i.toString();

            //AT努力値
            Spinner ATdoryokutida = (Spinner) findViewById(R.id.at3);
            idg = ATdoryokutida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String atdoryokuti = i.toString();

            //df努力値
            Spinner dfdoryokutida = (Spinner) findViewById(R.id.df3);
            idg = dfdoryokutida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String dfdoryokuti = i.toString();

            //spat努力値
            Spinner spatdoryokutida = (Spinner) findViewById(R.id.spat3);
            idg = spatdoryokutida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String spatdoryokuti = i.toString();

            //spdf努力値
            Spinner spdfdoryokutida = (Spinner) findViewById(R.id.spdf3);
            idg = spatdoryokutida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String spdfdoryokuti = i.toString();

            //spd努力値
            Spinner spddoryokutida = (Spinner) findViewById(R.id.spdd3);
            idg = spddoryokutida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String spddoryokuti = i.toString();

            //技1
            Spinner waza1da = (Spinner) findViewById(R.id.waza1);
            idg = waza1da.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String waza1 = i.toString();

            //技2
            Spinner waza2da = (Spinner) findViewById(R.id.waza2);
            idg = waza2da.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String waza2 = i.toString();

            //技3
            Spinner waza3da = (Spinner) findViewById(R.id.waza3);
            idg = waza3da.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String waza3 = i.toString();

            //技4
            Spinner waza4da = (Spinner) findViewById(R.id.waza4);
            idg = waza4da.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String waza4 = i.toString();

            //メモ
            EditText memoda = findViewById(R.id.memoedit);
            String memo = memoda.getText().toString();
            //性格
            Spinner seikakuda = (Spinner)findViewById(R.id.seikaku);
            idg = seikakuda.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String seikaku = i.toString();

            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("ID", noid);
            values.put("tokusei", tokusei);
            values.put("level", level);
            values.put("hpkotaiti", hpkotaiti);
            values.put("atkotaiti", atkotaiti);
            values.put("dfkotaiti", dfkotaiti);
            values.put("spatkotaiti", spatkotaiti);
            values.put("spdfkotaiti", spdfkotaiti);
            values.put("spdkotaiti", spdkotaiti);
            values.put("hpdoryokuti", hpdoryokuti);
            values.put("atdoryokuti", atdoryokuti);
            values.put("dfdoryokuti", dfdoryokuti);
            values.put("spatdoryokuti", spatdoryokuti);
            values.put("spdfdoryokuti", spdfdoryokuti);
            values.put("spddoryokuti", spddoryokuti);
            values.put("waza1", waza1);
            values.put("waza2", waza2);
            values.put("waza3", waza3);
            values.put("waza4", waza4);
            values.put("memo", memo);
            values.put("seikaku", seikaku);


            db.insert("pokemondb", null, values);
        }
        else{
            UPData(key);

        }
        Toast.makeText(getApplicationContext(),nameb+"しました",Toast.LENGTH_SHORT).show();
        finish();
    }


    /**
     * データ更新
     * @param read
     */
    public void UPData(String read){
        SQLiteDatabase db = helper.getWritableDatabase();

        //名前
        TextView nadata = findViewById(R.id.name);
        String name = nadata.getText().toString();

        //ID
        TextView noda = findViewById(R.id.no1);
        String noid = noda.getText().toString();


        //特性
        Spinner tokuda = (Spinner)findViewById(R.id.tokusei);

        int idg = tokuda.getSelectedItemPosition();
        Integer i = Integer.valueOf(idg);
        String tokusei = i.toString();

        //レベル
        Spinner levelda = (Spinner)findViewById(R.id.levelsp);
        idg = levelda.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String level = i.toString();

        //HP個体値
        Spinner hpkotaitida = (Spinner)findViewById(R.id.hp2);
        idg = hpkotaitida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String hpkotaiti = i.toString();

        //AT個体値
        Spinner ATkotaitida = (Spinner)findViewById(R.id.at2);
        idg = ATkotaitida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String atkotaiti = i.toString();

        //df個体値
        Spinner dfkotaitida = (Spinner)findViewById(R.id.df2);
        idg = dfkotaitida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String dfkotaiti = i.toString();

        //spat個体値
        Spinner spatkotaitida = (Spinner)findViewById(R.id.spat2);
        idg = spatkotaitida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String spatkotaiti = i.toString();

        //spdf個体値
        Spinner spdfkotaitida = (Spinner)findViewById(R.id.spdf2);
        idg = spdfkotaitida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String spdfkotaiti = i.toString();

        //spd個体値
        Spinner spdkotaitida = (Spinner)findViewById(R.id.spdd2);
        idg = spdfkotaitida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String spdkotaiti =i.toString();

        //HP努力値
        Spinner hpdoryokutida = (Spinner)findViewById(R.id.hp3);
        idg = hpdoryokutida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String hpdoryokuti = i.toString();

        //AT努力値
        Spinner ATdoryokutida = (Spinner)findViewById(R.id.at3);
        idg = ATdoryokutida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String atdoryokuti = i.toString();

        //df努力値
        Spinner dfdoryokutida = (Spinner)findViewById(R.id.df3);
        idg = dfdoryokutida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String dfdoryokuti = i.toString();

        //spat努力値
        Spinner spatdoryokutida = (Spinner)findViewById(R.id.spat3);
        idg = spatdoryokutida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String spatdoryokuti = i.toString();

        //spdf努力値
        Spinner spdfdoryokutida = (Spinner)findViewById(R.id.spdf3);
        idg = spatdoryokutida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String spdfdoryokuti = i.toString();

        //spd努力値
        Spinner spddoryokutida = (Spinner)findViewById(R.id.spdd3);
        idg = spddoryokutida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String spddoryokuti = i.toString();

        //技1
        Spinner waza1da = (Spinner)findViewById(R.id.waza1);
        idg = waza1da.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String waza1 = i.toString();

        //技2
        Spinner waza2da = (Spinner)findViewById(R.id.waza2);
        idg = waza2da.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String waza2 = i.toString();

        //技3
        Spinner waza3da = (Spinner)findViewById(R.id.waza3);
        idg = waza3da.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String waza3 = i.toString();

        //技4
        Spinner waza4da = (Spinner)findViewById(R.id.waza4);
        idg = waza4da.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String waza4 = i.toString();

        //メモ
        EditText memoda = findViewById(R.id.memoedit);
        String memo = memoda.getText().toString();

        //性格
        Spinner seikakuda = (Spinner)findViewById(R.id.seikaku);
        idg = seikakuda.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String seikaku = i.toString();

        ContentValues upvalue = new ContentValues();
        upvalue.put("name",name);
        upvalue.put("name",name);
        upvalue.put("ID",noid);
        upvalue.put("tokusei",tokusei);
        upvalue.put("level",level);
        upvalue.put("hpkotaiti",hpkotaiti);
        upvalue.put("atkotaiti",atkotaiti);
        upvalue.put("dfkotaiti",dfkotaiti);
        upvalue.put("spatkotaiti",spatkotaiti);
        upvalue.put("spdfkotaiti",spdfkotaiti);
        upvalue.put("spdkotaiti",spdkotaiti);
        upvalue.put("hpdoryokuti",hpdoryokuti);
        upvalue.put("atdoryokuti",atdoryokuti);
        upvalue.put("dfdoryokuti",dfdoryokuti);
        upvalue.put("spatdoryokuti",spatdoryokuti);
        upvalue.put("spdfdoryokuti",spdfdoryokuti);
        upvalue.put("spddoryokuti",spddoryokuti);
        upvalue.put("waza1",waza1);
        upvalue.put("waza2",waza2);
        upvalue.put("waza3",waza3);
        upvalue.put("waza4",waza4);
        upvalue.put("memo",memo);
        upvalue.put("seikaku",seikaku);


        db.update("pokemondb",upvalue,"_id=?",new String[]{read});
    }



    /**
     * データを参照する
     * @param read
     */
    public  void readDate(String read){
        SQLiteDatabase db = helper.getReadableDatabase();
        //name
        TextView name = findViewById(R.id.name);


        Cursor cursor = db.query(
                "pokemondb",
                new String[]{"name","ID","tokusei" ,"level","hpkotaiti","atkotaiti","dfkotaiti","spatkotaiti","spdfkotaiti","spdkotaiti","hpdoryokuti","atdoryokuti",
                "dfdoryokuti","spatdoryokuti","spdfdoryokuti","spddoryokuti","waza1","waza2","waza3","waza4","memo","seikaku"},
                "_ID = ?",
                new String[]{read},
                null,null,null
        );
        cursor.moveToFirst();

        //tokusei
        Spinner tokuseisp = (Spinner) findViewById(R.id.tokusei);
        String idg = cursor.getString(2);
        int tid = Integer.parseInt(idg);
        tokuseisp.setSelection(tid);
        //level
        Spinner level = (Spinner) findViewById(R.id.levelsp);
        idg = cursor.getString(3);
        tid = Integer.parseInt(idg);
        level.setSelection(tid);
        //hpkotaiti
        Spinner hpkotaiti = (Spinner) findViewById(R.id.hp2);
        idg = cursor.getString(4);
        tid = Integer.parseInt(idg);
        hpkotaiti.setSelection(tid);
        //atkotaiti
        Spinner atkotaiti = (Spinner) findViewById(R.id.at2);
        idg = cursor.getString(5);
        tid = Integer.parseInt(idg);
        atkotaiti.setSelection(tid);
        //dfkotaiti
        Spinner dfkotaiti = (Spinner) findViewById(R.id.df2);
        idg = cursor.getString(6);
        tid = Integer.parseInt(idg);
        dfkotaiti.setSelection(tid);
        //spatkotaiti
        Spinner spatkotaiti = (Spinner) findViewById(R.id.spat2);
        idg = cursor.getString(7);
        tid = Integer.parseInt(idg);
        spatkotaiti.setSelection(tid);
        //spdfkotaiti
        Spinner spdfkotaiti = (Spinner) findViewById(R.id.spdf2);
        idg = cursor.getString(8);
        tid = Integer.parseInt(idg);
        spdfkotaiti.setSelection(tid);
        //spdkotaiti
        Spinner spdkotaiti = (Spinner) findViewById(R.id.spdd2);
        idg = cursor.getString(9);
        tid = Integer.parseInt(idg);
        spdkotaiti.setSelection(tid);
        //hpdoryokuti
        Spinner hpdoryokuti = (Spinner) findViewById(R.id.hp3);
        idg = cursor.getString(10);
        tid = Integer.parseInt(idg);
        hpdoryokuti.setSelection(tid);
        //atdoryokuti
        Spinner atdoryokuti = (Spinner) findViewById(R.id.at3);
        idg = cursor.getString(11);
        tid = Integer.parseInt(idg);
        atdoryokuti.setSelection(tid);
        //dfdoryokuti
        Spinner dfdoryokuti = (Spinner) findViewById(R.id.df3);
        idg = cursor.getString(12);
        tid = Integer.parseInt(idg);
        dfdoryokuti.setSelection(tid);
        //spatdoryokuti
        Spinner spatdoryokuti = (Spinner) findViewById(R.id.spat3);
        idg = cursor.getString(13);
        tid = Integer.parseInt(idg);
        spatdoryokuti.setSelection(tid);
        //spdfdoryokuti
        Spinner spdfdoryokuti = (Spinner) findViewById(R.id.spdf3);
        idg = cursor.getString(14);
        tid = Integer.parseInt(idg);
        spdfdoryokuti.setSelection(tid);
        //spddoryokuti
        Spinner spddoryokuti = (Spinner) findViewById(R.id.spdd3);
        idg = cursor.getString(15);
        tid = Integer.parseInt(idg);
        spddoryokuti.setSelection(tid);
        //waza1
        Spinner waza1 = (Spinner) findViewById(R.id.waza1);
        idg = cursor.getString(16);
        tid = Integer.parseInt(idg);
        waza1.setSelection(tid);
        //waza2
        Spinner waza2 = (Spinner) findViewById(R.id.waza2);
        idg = cursor.getString(17);
        tid = Integer.parseInt(idg);
        waza2.setSelection(tid);
        //waza3
        Spinner waza3 = (Spinner) findViewById(R.id.waza3);
        idg = cursor.getString(18);
        tid = Integer.parseInt(idg);
        waza3.setSelection(tid);
        //waza4
        Spinner waza4 = (Spinner) findViewById(R.id.waza4);
        idg = cursor.getString(19);
        tid = Integer.parseInt(idg);
        waza4.setSelection(tid);
        //memo
        EditText memo= findViewById(R.id.memoedit);
        idg=cursor.getString(20);
        memo.setText(idg);
        //性格
        Spinner seikaku = (Spinner) findViewById(R.id.seikaku);
        idg = cursor.getString(21);
        tid = Integer.parseInt(idg);
        seikaku.setSelection(tid);


        cursor.close();


    }


}