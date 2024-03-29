package com.example.pokemonapp2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PTDB extends SQLiteOpenHelper {
    //データベースのバージョン
    private static final int DATABASE_VERSION = 1;

    //データベース情報を変数に格納
    private static final String DATABASE_NAME = "pokemonPT.db";
    private static final String TABLE_NAME = "ptdb";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_ID1 = "ID1";
    private static final String COLUMN_NAME_ID2 = "ID2";
    private static final String COLUMN_NAME_ID3 = "ID3";
    private static final String COLUMN_NAME_ID4 = "ID4";
    private static final String COLUMN_NAME_ID5 = "ID5";
    private static final String COLUMN_NAME_ID6 = "ID6";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_ID1 + " TEXT," +
                    COLUMN_NAME_ID2 + " TEXT," +
                    COLUMN_NAME_ID3 + " TEXT," +
                    COLUMN_NAME_ID4 + " TEXT," +
                    COLUMN_NAME_ID5 + " TEXT," +
                    COLUMN_NAME_ID6 + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS" + TABLE_NAME;

    PTDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db,int oldVersion,int newVersion){
        onUpgrade(db,oldVersion,newVersion);
    }
}
