package com.example.pokemonapp2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class dbadd extends DialogFragment {
    Context d;
    public dbadd(Context c) {
        d=c;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // カスタムレイアウトの用意
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View customAlertView = inflater.inflate(R.layout.dbdailog, null);

        // ダイアログの作成
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(customAlertView);

        Button dbaddb = customAlertView.findViewById(R.id.button2);


        //追加
        dbaddb.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                ptOpenHelper helper;
                helper = new ptOpenHelper(d);
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues cv = new ContentValues();

                String pt1 = "1";
                String pt2 = "1";

                cv.put("ID", "1");
                cv.put("ID2", "1");
                cv.put("ID3", "1");
                cv.put("ID4", "1");
                cv.put("ID5", "1");
                cv.put("ID6", "1");

                db.insert("pokemonptdb", null, cv);

                Toast.makeText(d, "登録しました", Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(d, ptselect.class);
                startActivity(intent4);
            }
        });
        
        return builder.create();
    }


}
