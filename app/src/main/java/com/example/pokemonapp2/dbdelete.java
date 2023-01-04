package com.example.pokemonapp2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class dbdelete extends DialogFragment {
    Context d;
    int dbp;
    public dbdelete(Context c,int tabposition) {
        d=c;
        dbp=tabposition;
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
        TextView textd = customAlertView.findViewById(R.id.textView3);
        textd.setText("このパーティを削除しますか");
        dbaddb.setText("削除");

        //追加
        dbaddb.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                ptOpenHelper helper;
                helper = new ptOpenHelper(d);
                SQLiteDatabase db = helper.getWritableDatabase();
                int numRows = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM pokemonptdb", null);
                if(!(numRows==1)){
                db.beginTransaction();
                try {
                    db.delete("pokemonptdb", "_id=?", new String[]{String.valueOf(dbp+1)});
                    db.setTransactionSuccessful();
                    //カラムの数を数える

                    ContentValues upvalue = new ContentValues();
                    //_idのずれを訂正
                    for(int i=dbp+1;i<numRows;i++){
                        upvalue.put("_id", String.valueOf(i));
                        db.update("pokemonptdb", upvalue, "_id=?", new String[]{String.valueOf(i)});

                    }
                } finally {
                    db.endTransaction();
                }


                Intent intent4 = new Intent(d, ptselect.class);
                startActivity(intent4);}
            }
        });

        return builder.create();
    }


}