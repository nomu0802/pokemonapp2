package com.example.pokemonapp2;

import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class jsonclass extends  poketouroku {

    void json() {
        String jsondata = null;
        AssetManager assetManager;
        InputStream is = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.getAssets().open("pokedata.json")));
            jsondata = br.readLine();
            JSONArray datas = new JSONArray(jsondata);} catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
