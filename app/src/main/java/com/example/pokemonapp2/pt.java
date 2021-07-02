package com.example.pokemonapp2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class pt extends DialogFragment {
    ArrayList<String> dougu = new ArrayList<>();
    ArrayList data = new ArrayList<>();
    ArrayList data2 = new ArrayList<>();
    ArrayList data3 = new ArrayList<>();
    String id;
    String name;
    Context d;
    String postion;
    String Ptid;
    public pt(Context c, String idx, String nm, String pos,String ptid) {
        d=c;
        id = idx;
        name = nm;
        postion=pos;
        Ptid = ptid;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        // カスタムレイアウトの用意
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View customAlertView = inflater.inflate(R.layout.pt_dialog, null);
        db(postion);

        ImageView spm = customAlertView.findViewById(R.id.ptdairogimg);
        Bitmap image =getBitmapFromAsset();
        spm.setImageBitmap(image);
        // ダイアログの作成
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(customAlertView);


        TextView pkname = customAlertView.findViewById(R.id.pokename);
        pkname.setText(name);

        TextView postiont = customAlertView.findViewById(R.id.daialogitem);
        postiont.setText(postion);

        Button ch = customAlertView.findViewById(R.id.chengept);
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ins[] = {"1","potion",id,Ptid};
                Intent intent3 = new Intent(d.getApplicationContext(), pokegrid.class);
                intent3.putExtra("KEY",ins);
                startActivity(intent3);

            }
        });



        return builder.create();
    }


    //画像の読み込み
    private Bitmap getBitmapFromAsset() {
        AssetManager assetManager = d.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open("sprites/"+idg+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }


    String idg;
    String level;
    String douguname;
    public void db(String read){

        //データの参照
        //ptOpenhelperから外部キーで取得
        MyOpenHelper helper2;
        helper2 = new MyOpenHelper(d);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor = db.query(
                "pokemondb",
                new String[]{"ID","name","level","dougu"},
                "_ID = ?",
                new String[]{read},
                null, null, null
        );

        cursor.moveToFirst();
        idg = cursor.getString(0);
        name= cursor.getString(1);
        int levela = Integer.parseInt(cursor.getString(2))+1;
        level = String.valueOf(levela);
        douguname = cursor.getString(3);
        cursor.close();

    }

}

