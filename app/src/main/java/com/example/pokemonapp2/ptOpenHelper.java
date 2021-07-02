package com.example.pokemonapp2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static org.xmlpull.v1.XmlPullParser.TEXT;

public class ptOpenHelper extends SQLiteOpenHelper {

    //データベースのバージョン
    private static final int DATABASE_VERSION = 1;

    //データベース情報を変数に格納
    private static final String DATABASE_NAME2 = "pokemonpt.db";
    private static final String TABLE_NAME2 = "pokemonptdb";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_ID = "ID";
    private static final String COLUMN_NAME_ID2 = "ID2";
    private static final String COLUMN_NAME_ID3 = "ID3";
    private static final String COLUMN_NAME_ID4 = "ID4";
    private static final String COLUMN_NAME_ID5 = "ID5";
    private static final String COLUMN_NAME_ID6 = "ID6";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME2 + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_ID + " TEXT," +
                    COLUMN_NAME_ID2 + " TEXT," +
                    COLUMN_NAME_ID3 + " TEXT," +
                    COLUMN_NAME_ID4 + " TEXT," +
                    COLUMN_NAME_ID5 + " TEXT," +
                    COLUMN_NAME_ID6 + " TEXT)";


    private static final String SQL_DELETE_ENTRIES2 =
            "DROP TABLE IF EXISTS" + TABLE_NAME2;

    ptOpenHelper(Context context) {
        super(context, DATABASE_NAME2, null, DATABASE_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL(
                SQL_DELETE_ENTRIES2
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db,int oldVersion,int newVersion){
        onUpgrade(db,oldVersion,newVersion);
    }
}

