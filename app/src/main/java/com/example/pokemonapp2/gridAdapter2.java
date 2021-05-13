package com.example.pokemonapp2;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class gridAdapter2 extends BaseAdapter {


    String noname;
    private Context context;
    private LayoutInflater inflater;
    private  String[] numberWord;
    private  int [] numberImage;

    public gridAdapter2(Context c, String[] numberWord, int[] numberImage){
        context = c;
        this.numberWord = numberWord;
        this.numberImage = numberImage;

    }

    @Override
    public int getCount() {
        return numberWord.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int potion, View convertView, ViewGroup parent) {

        if (inflater ==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.row_item2,null);
        }

        ImageView imageView = convertView.findViewById(R.id.image_view);
        Bitmap image = getBitmapFromAsset(numberWord[potion]);


        int num = Integer.parseInt(numberWord[potion]);

        String name = json(num-1);

        imageView.setImageBitmap(image);
        return convertView;

    }



    private Bitmap getBitmapFromAsset(String strName){
        AssetManager mngr = context.getAssets();
        InputStream is = null;

        try {
            is = mngr.open("sprites/"+strName+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }


    public String json(int i){
        //json読み込み
        String jsondata = null;
        AssetManager assetManager;
        InputStream is = null;
        ArrayList data = new ArrayList<>();
        String name = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("pokemon.json")));
            jsondata = br.readLine();
            JSONArray json1 = new JSONArray(jsondata);
            JSONObject json = json1.getJSONObject(i);
            //ポケモンの名前
            name = json.getString("name");
            noname = json.getString("no");

        }
        catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return  name;


    }



}
