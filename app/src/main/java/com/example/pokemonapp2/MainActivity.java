package com.example.pokemonapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bt(View view) {
        Intent intent = new Intent(getApplication(), pokesorch.class);
        startActivity(intent);
    }

    public void boxboutton(View view) {
        String ins[] = {"0"};
        Intent intent3 = new Intent(getApplication(), pokegrid.class);
        intent3.putExtra("KEY",ins);
        startActivity(intent3);
    }

    public void ptboutton(View view) {

        MyOpenHelper myhelper = new MyOpenHelper(MainActivity.this);
        SQLiteDatabase db = myhelper.getReadableDatabase();
        //カラムの数を数える
        int numRows = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM pokemondb", null);

        if(numRows==0){ Toast.makeText(getApplicationContext(), "登録してください", Toast.LENGTH_SHORT).show();}

        else {


        ptOpenHelper helper = new ptOpenHelper(MainActivity.this);
        SQLiteDatabase db2 = helper.getReadableDatabase();
        //カラムの数を数える
        int numRows2 = (int) DatabaseUtils.longForQuery(db2, "SELECT COUNT(*) FROM pokemonptdb", null);

        if(numRows2==0){

            ptadd();
            Intent intent4 = new Intent(getApplication(), ptselect.class);
            startActivity(intent4);
        }
        else{

        Intent intent4 = new Intent(getApplication(), ptselect.class);
        startActivity(intent4);}
        }
    }

    public void dbs(View view) {

         PTDB helper;
        helper = new PTDB(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("ID1","9999");
        values.put("ID2","9999");
        values.put("ID3","9999");
        values.put("ID4","9999");
        values.put("ID5","9999");
        values.put("ID6","9999");

        db.insert("ptdb", null, values);

    }


    public void ptadd() {
       ptOpenHelper helper;
        helper = new ptOpenHelper(getApplicationContext());
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


    }
}