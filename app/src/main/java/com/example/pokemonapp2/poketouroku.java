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

import static android.graphics.Color.parseColor;
import static android.icu.text.MessagePattern.ArgType.SELECT;

public class poketouroku extends AppCompatActivity {
    ArrayList<String> seikaku = new ArrayList<String>();
    int index;
    String no;
    String name="";
    ArrayList<String> typelist = new ArrayList<String>();
    ArrayList<String> tokuseili = new ArrayList<>();
    ArrayList<String> syuzokuli = new ArrayList<>();
    ArrayList<String> wazalist2 = new ArrayList<String>();
    ArrayList<String> wazali = new ArrayList<>();
    ArrayList<String> wazali3 = new ArrayList<>();
    ArrayList<String> formli = new ArrayList<>();
    ArrayList<String> levellist = new ArrayList<>();
    ArrayList<String> memoli = new ArrayList<>();
    String key = "0";
    String ibent= "0";
    private MyOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poketouroku);

        //ViewPagerの取得
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        //TabLayoutを取得してそれをViewPagerにセット
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);

        tabLayout.setupWithViewPager(viewPager);

        //Adapterを取得してそれをViewPagerにセット

        tourokuViewPagerAdapter viewPagerAdapter = new tourokuViewPagerAdapter(getSupportFragmentManager(),syuzokuli,wazali,wazali3,memoli);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.getTabAt(0).setCustomView(R.layout.tourokutabat0);
        tabLayout.getTabAt(1).setCustomView(R.layout.tourokutabat1);
        tabLayout.getTabAt(2).setCustomView(R.layout.tourokutabat2);



        //データの受け取り
        Intent intent = getIntent();
        String idex[] = intent.getStringArrayExtra("KEY");
        String id = idex[0];
        index = Integer.parseInt(id);
        String name = idex[1];
        key = idex[2];

        //性格を設定
        seikaku();

        //json設定
        json();


        //データの設定
        //ポケモン名前
        TextView pokename = findViewById(R.id.name);
        pokename.setText(name);
        //ポケモンID
        TextView pokeid = findViewById(R.id.no1);
        pokeid.setText(no);
        //type;
        type();
        //画像のセット
        //小さいほう
        ImageView spm = findViewById(R.id.sprites);
        Bitmap image =getBitmapFromAsset("sprites/"+index);
        spm.setImageBitmap(image);
        //大きいほう
        ImageView thum = findViewById(R.id.pokeimg);
        Bitmap image2 =getBitmapFromAsset("thumbnails/"+index);
        thum.setImageBitmap(image2);
        //特性
        ArrayAdapter<String> tokusei = new ArrayAdapter<String>(this,R.layout.seikakuspinner_item, tokuseili);
        tokusei.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        Spinner tokuseisp = (Spinner) findViewById(R.id.tokusei);
        tokuseisp.setAdapter(tokusei);
        //フォルム
        ArrayAdapter<String> forma = new ArrayAdapter<String>(this,R.layout.seikakuspinner_item, formli);
        forma.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        Spinner formsp = (Spinner) findViewById(R.id.forumspin);
        formsp.setAdapter(forma);
        //レベル
        level();

        //DB作成
        helper = new MyOpenHelper(getApplicationContext());
        if (key.length() != 0) {
            Button buton = findViewById(R.id.tourokubutton);
            buton.setText("修正");
            ibent="2";
            syuzokuli.add(ibent);
            wazali3.add(ibent);
            memoli.add(ibent);
            readDate(key);


        }
        syuzokuli.add(ibent);
        wazali3.add(ibent);
        memoli.add(ibent);

    }

    //あとで消す
    public void test2(View view) {
        Spinner test = findViewById(R.id.hpspinner1);
        int idg = test.getSelectedItemPosition();
        Integer i = Integer.valueOf(idg);
        String tete = i.toString();

        Toast.makeText(poketouroku.this, tete, Toast.LENGTH_SHORT).show();
    }

    //レベルスピナー設定
    public void level(){
        for(int i=1;i<=100;i++){

            levellist.add(String.valueOf(i));
        }
        ArrayAdapter<String> levelad = new ArrayAdapter<String>(this,R.layout.seikakuspinner_item,levellist);
        levelad.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        Spinner levelspp = (Spinner) findViewById(R.id.levelsp);
        levelspp.setAdapter(levelad);
        levelspp.setSelection(49);

    }

    //性格スピナー設定
    public void seikaku(){
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
        ArrayAdapter<String> seikakud= new ArrayAdapter<String>(poketouroku.this, R.layout.seikakuspinner_item, seikaku);
        seikakud.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        Spinner seikaku = findViewById(R.id.seikaku);
        seikaku.setAdapter(seikakud);
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

            //名前
            name = jdata.getString("name");

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
        //2タイプ目の設定
        String color2= "";
        typelist.add(color2);
        color2 = satosi.typoe(typelist.get(1));
        type2.setColorFilter(Color.parseColor(color2), PorterDuff.Mode.SRC_IN);
        typevv2.setBackground(type2);
        typename2.setText(typelist.get(1));
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

    public void item(View view) {
        Context c = poketouroku.this;
        DialogFragment dialogFragment = new dougu(c);
        dialogFragment.show(getSupportFragmentManager(), "my_dialog2");
    }

    public void onReturnValue(String value) {
        TextView dougumei = findViewById(R.id.dougumei);
        dougumei.setText(value);
    }

    public void wazaselect(String value,int id) {

        if(id==1){
            TextView wazamei1 = findViewById(R.id.wazawaku1);
            wazamei1.setText(value);
        }
        else if(id==2){
            TextView wazamei2 = findViewById(R.id.wazawaku2);
            wazamei2.setText(value);
        }
        else if(id==3){
            TextView wazamei3 = findViewById(R.id.wazawaku3);
            wazamei3.setText(value);
        }
        else{
            TextView wazamei4 = findViewById(R.id.wazawaku4);
            wazamei4.setText(value);
        }

    }

    public void saveData(View view) {
        Button tourokumei = findViewById(R.id.tourokubutton);
        String nameb = tourokumei.getText().toString();

        if (nameb.equals("登録")) {

            TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
            tabLayout.getTabAt(2).select();
            tabLayout.getTabAt(1).select();

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
            Spinner hpkotaitida = (Spinner) findViewById(R.id.hpspinner1);
            idg = hpkotaitida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String hpkotaiti = i.toString();

            //AT個体値
            Spinner ATkotaitida = (Spinner) findViewById(R.id.atspinner1);
            idg = ATkotaitida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String atkotaiti = i.toString();

            //df個体値
            Spinner dfkotaitida = (Spinner) findViewById(R.id.dfspinner1);
            idg = dfkotaitida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String dfkotaiti = i.toString();

            //spat個体値
            Spinner spatkotaitida = (Spinner) findViewById(R.id.spatspinner1);
            idg = spatkotaitida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String spatkotaiti = i.toString();

            //spdf個体値
            Spinner spdfkotaitida = (Spinner) findViewById(R.id.spdfspinner1);
            idg = spdfkotaitida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String spdfkotaiti = i.toString();

            //spd個体値
            Spinner spdkotaitida = (Spinner) findViewById(R.id.spdspinner1);
            idg = spdfkotaitida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String spdkotaiti = i.toString();

            //HP努力値
            Spinner hpdoryokutida = (Spinner) findViewById(R.id.hpspinner2);
            idg = hpdoryokutida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String hpdoryokuti = i.toString();

            //AT努力値
            Spinner ATdoryokutida = (Spinner) findViewById(R.id.atspinner2);
            idg = ATdoryokutida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String atdoryokuti = i.toString();

            //df努力値
            Spinner dfdoryokutida = (Spinner) findViewById(R.id.dfspinner2);
            idg = dfdoryokutida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String dfdoryokuti = i.toString();

            //spat努力値
            Spinner spatdoryokutida = (Spinner) findViewById(R.id.spatspinner2);
            idg = spatdoryokutida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String spatdoryokuti = i.toString();

            //spdf努力値
            Spinner spdfdoryokutida = (Spinner) findViewById(R.id.spdfspinner2);
            idg = spatdoryokutida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String spdfdoryokuti = i.toString();

            //spd努力値
            Spinner spddoryokutida = (Spinner) findViewById(R.id.spdspinner2);
            idg = spddoryokutida.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String spddoryokuti = i.toString();

            //技1
            TextView waza1da = findViewById(R.id.wazawaku1);
            String waza1= waza1da.getText().toString();

            //技2
            TextView waza2da = findViewById(R.id.wazawaku2);
            String waza2= waza2da.getText().toString();

            //技3
            TextView waza3da = findViewById(R.id.wazawaku3);
            String waza3= waza3da.getText().toString();

            //技4
            TextView waza4da = findViewById(R.id.wazawaku4);
            String waza4= waza4da.getText().toString();

            //メモ
            EditText memoda = findViewById(R.id.stwaku1);
            String memo = memoda.getText().toString();

            //性格
            Spinner seikakuda = (Spinner) findViewById(R.id.seikaku);
            idg = seikakuda.getSelectedItemPosition();
            i = Integer.valueOf(idg);
            String seikaku = i.toString();

            //道具
            TextView dougd = findViewById(R.id.dougumei);
            String dougu = (String) dougd.getText();

            //ニックネーム
            EditText nicnamed = findViewById(R.id.editname);
            String nicname = String.valueOf(nicnamed.getText());

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
            values.put("dougu", dougu);
            values.put("nickname",nicname);
            db.insert("pokemondb", null, values);
            Toast.makeText(getApplicationContext(), "登録" + "しました", Toast.LENGTH_SHORT).show();
        } else {

            UPData(key);

        }

        finish();
    }
    /**
     * データ更新
     *
     * @param read
     */
    public void UPData(String read) {
        SQLiteDatabase db = helper.getWritableDatabase();
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.getTabAt(2).select();
        tabLayout.getTabAt(1).select();

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
        Spinner hpkotaitida = (Spinner) findViewById(R.id.hpspinner1);
        idg = hpkotaitida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String hpkotaiti = i.toString();

        //AT個体値
        Spinner ATkotaitida = (Spinner) findViewById(R.id.atspinner1);
        idg = ATkotaitida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String atkotaiti = i.toString();

        //df個体値
        Spinner dfkotaitida = (Spinner) findViewById(R.id.dfspinner1);
        idg = dfkotaitida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String dfkotaiti = i.toString();

        //spat個体値
        Spinner spatkotaitida = (Spinner) findViewById(R.id.spatspinner1);
        idg = spatkotaitida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String spatkotaiti = i.toString();

        //spdf個体値
        Spinner spdfkotaitida = (Spinner) findViewById(R.id.spdfspinner1);
        idg = spdfkotaitida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String spdfkotaiti = i.toString();

        //spd個体値
        Spinner spdkotaitida = (Spinner) findViewById(R.id.spdspinner1);
        idg = spdfkotaitida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String spdkotaiti = i.toString();

        //HP努力値
        Spinner hpdoryokutida = (Spinner) findViewById(R.id.hpspinner2);
        idg = hpdoryokutida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String hpdoryokuti = i.toString();

        //AT努力値
        Spinner ATdoryokutida = (Spinner) findViewById(R.id.atspinner2);
        idg = ATdoryokutida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String atdoryokuti = i.toString();

        //df努力値
        Spinner dfdoryokutida = (Spinner) findViewById(R.id.dfspinner2);
        idg = dfdoryokutida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String dfdoryokuti = i.toString();

        //spat努力値
        Spinner spatdoryokutida = (Spinner) findViewById(R.id.spatspinner2);
        idg = spatdoryokutida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String spatdoryokuti = i.toString();

        //spdf努力値
        Spinner spdfdoryokutida = (Spinner) findViewById(R.id.spdfspinner2);
        idg = spatdoryokutida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String spdfdoryokuti = i.toString();

        //spd努力値
        Spinner spddoryokutida = (Spinner) findViewById(R.id.spdspinner2);
        idg = spddoryokutida.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String spddoryokuti = i.toString();

        //技1
        TextView waza1da = findViewById(R.id.wazawaku1);
        String waza1= waza1da.getText().toString();

        //技2
        TextView waza2da = findViewById(R.id.wazawaku2);
        String waza2= waza2da.getText().toString();
        //技3
        TextView waza3da = findViewById(R.id.wazawaku3);
        String waza3= waza3da.getText().toString();

        //技4
        TextView waza4da = findViewById(R.id.wazawaku4);
        String waza4= waza4da.getText().toString();

        //メモ
        EditText memoda = findViewById(R.id.stwaku1);
        String memo = memoda.getText().toString();

        //性格
        Spinner seikakuda = (Spinner) findViewById(R.id.seikaku);
        idg = seikakuda.getSelectedItemPosition();
        i = Integer.valueOf(idg);
        String seikaku = i.toString();

        //ニックネーム
        EditText nicnamed = findViewById(R.id.editname);
        String nicname = String.valueOf(nicnamed.getText());

        //道具
        TextView dougd = findViewById(R.id.dougumei);
        String dougu = (String) dougd.getText();
        ContentValues upvalue = new ContentValues();
        upvalue.put("name", name);
        upvalue.put("name", name);
        upvalue.put("ID", noid);
        upvalue.put("tokusei", tokusei);
        upvalue.put("level", level);
        upvalue.put("hpkotaiti", hpkotaiti);
        upvalue.put("atkotaiti", atkotaiti);
        upvalue.put("dfkotaiti", dfkotaiti);
        upvalue.put("spatkotaiti", spatkotaiti);
        upvalue.put("spdfkotaiti", spdfkotaiti);
        upvalue.put("spdkotaiti", spdkotaiti);
        upvalue.put("hpdoryokuti", hpdoryokuti);
        upvalue.put("atdoryokuti", atdoryokuti);
        upvalue.put("dfdoryokuti", dfdoryokuti);
        upvalue.put("spatdoryokuti", spatdoryokuti);
        upvalue.put("spdfdoryokuti", spdfdoryokuti);
        upvalue.put("spddoryokuti", spddoryokuti);
        upvalue.put("waza1", waza1);
        upvalue.put("waza2", waza2);
        upvalue.put("waza3", waza3);
        upvalue.put("waza4", waza4);
        upvalue.put("memo", memo);
        upvalue.put("seikaku", seikaku);
        upvalue.put("dougu", dougu);
        upvalue.put("nickname",nicname);
        db.update("pokemondb", upvalue, "_id=?", new String[]{read});
        Toast.makeText(getApplicationContext(), "登録" + "しました", Toast.LENGTH_SHORT).show();
    }


    /**
     * データを参照する
     *
     * @param read
     */
    public void readDate(String read) {
        SQLiteDatabase db = helper.getReadableDatabase();
        //name
        TextView name = findViewById(R.id.name);


        Cursor cursor = db.query(
                "pokemondb",
                new String[]{"name", "ID", "tokusei", "level", "hpkotaiti", "atkotaiti", "dfkotaiti", "spatkotaiti", "spdfkotaiti", "spdkotaiti", "hpdoryokuti", "atdoryokuti",
                        "dfdoryokuti", "spatdoryokuti", "spdfdoryokuti", "spddoryokuti", "waza1", "waza2", "waza3", "waza4", "memo", "seikaku", "dougu","nickname"},
                "_ID = ?",
                new String[]{read},
                null, null, null
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
        Spinner hpkotaiti = (Spinner) findViewById(R.id.hpspinner1);
        idg = cursor.getString(4);
        tid = Integer.parseInt(idg);
        syuzokuli.add(idg);
        //atkotaiti
         Spinner atkotaiti = (Spinner) findViewById(R.id.atspinner1);
        idg = cursor.getString(5);
        tid = Integer.parseInt(idg);
        syuzokuli.add(idg);
        //dfkotaiti
        Spinner dfkotaiti = (Spinner) findViewById(R.id.dfspinner1);
        idg = cursor.getString(6);
        tid = Integer.parseInt(idg);
        syuzokuli.add(idg);
        //spatkotaiti
        Spinner spatkotaiti = (Spinner) findViewById(R.id.spatspinner1);
        idg = cursor.getString(7);
        tid = Integer.parseInt(idg);
        syuzokuli.add(idg);
        //spdfkotaiti
        Spinner spdfkotaiti = (Spinner) findViewById(R.id.spdfspinner1);
        idg = cursor.getString(8);
        tid = Integer.parseInt(idg);
        syuzokuli.add(idg);
        //spdkotaiti
        Spinner spdkotaiti = (Spinner) findViewById(R.id.spdspinner1);
        idg = cursor.getString(9);
        tid = Integer.parseInt(idg);
        syuzokuli.add(idg);
        //hpdoryokuti
        Spinner hpdoryokuti = (Spinner) findViewById(R.id.hpspinner2);
        idg = cursor.getString(10);
        tid = Integer.parseInt(idg);
        syuzokuli.add(idg);
        //atdoryokuti
        Spinner atdoryokuti = (Spinner) findViewById(R.id.atspinner2);
        idg = cursor.getString(11);
        tid = Integer.parseInt(idg);
        syuzokuli.add(idg);
        //dfdoryokuti
        Spinner dfdoryokuti = (Spinner) findViewById(R.id.dfspinner2);
        idg = cursor.getString(12);
        tid = Integer.parseInt(idg);
        syuzokuli.add(idg);
        //spatdoryokuti
        Spinner spatdoryokuti = (Spinner) findViewById(R.id.spatspinner2);
        idg = cursor.getString(13);
        tid = Integer.parseInt(idg);
        syuzokuli.add(idg);
        //spdfdoryokuti
        Spinner spdfdoryokuti = (Spinner) findViewById(R.id.spdfspinner2);
        idg = cursor.getString(14);
        tid = Integer.parseInt(idg);
        syuzokuli.add(idg);
        //spddoryokuti
        Spinner spddoryokuti = (Spinner) findViewById(R.id.spdspinner2);
        idg = cursor.getString(15);
        tid = Integer.parseInt(idg);
        syuzokuli.add(idg);
        //waza1
        TextView waza1 = findViewById(R.id.wazawaku1);
        idg = cursor.getString(16);
        wazali3.add(idg);
        //waza2
        TextView waza2 = findViewById(R.id.wazawaku2);
        idg = cursor.getString(17);
        wazali3.add(idg);
        //waza3
        TextView waza3 = findViewById(R.id.wazawaku3);
        idg = cursor.getString(18);
        wazali3.add(idg);
        //waza4
        TextView waza4 = findViewById(R.id.wazawaku4);
        idg = cursor.getString(19);

        wazali3.add(idg);
      //memo
        EditText memo = findViewById(R.id.stwaku1);
        idg = cursor.getString(20);
        memoli.add(idg);



        //性格
        Spinner seikaku = (Spinner) findViewById(R.id.seikaku);
        idg = cursor.getString(21);
        tid = Integer.parseInt(idg);
        seikaku.setSelection(tid);
        //道具
        TextView dougd = findViewById(R.id.dougumei);
        idg = cursor.getString(22);
        dougd.setText(idg);

        //ニックネーム
        EditText nicnamed = findViewById(R.id.editname);
        idg = cursor.getString(23);
        nicnamed.setText(idg);

        cursor.close();

    }



    public void test(View view) {
        //メモ
        TextView test = findViewById(R.id.wazawaku1);
        Toast.makeText(getApplicationContext(), test.getText() + "しました", Toast.LENGTH_SHORT).show();
    }
}