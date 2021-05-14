package com.example.pokemonapp2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

    //データベースのバージョン
    private static final int DATABASE_VERSION = 1;

    //データベース情報を変数に格納
    private static final String DATABASE_NAME = "pokemonapp.db";
    private static final String TABLE_NAME = "pokemondb";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_NAME = "name";
    private static final String COLUMN_NAME_ID = "ID";
    private static final String COLUMN_NAME_tokusei = "tokusei";
    private static final String COLUMN_NAME_level = "level";
    private static final String COLUMN_NAME_hpkotaiti ="hpkotaiti";
    private static final String COLUMN_NAME_atkotaiti ="atkotaiti";
    private static final String COLUMN_NAME_dfkotaiti = "dfkotaiti";
    private static final String COLUMN_NAME_spatkotaiti = "spatkotaiti";
    private static final String COLUMN_NAME_spdfkotaiti = "spdfkotaiti";
    private static final String COLUMN_NAME_spdkotaiti = "spdkotaiti";
    private static final String COLUMN_NAME_hpdoryokuti ="hpdoryokuti";
    private static final String COLUMN_NAME_atdoryokuti ="atdoryokuti";
    private static final String COLUMN_NAME_dfdoryokuti = "dfdoryokuti";
    private static final String COLUMN_NAME_spatdoryokuti = "spatdoryokuti";
    private static final String COLUMN_NAME_spdfdoryokuti = "spdfdoryokuti";
    private static final String COLUMN_NAME_spddoryokuti = "spddoryokuti";
    private static final String COLUMN_NAME_waza1 = "waza1";
    private static final String COLUMN_NAME_waza2 = "waza2";
    private static final String COLUMN_NAME_waza3 = "waza3";
    private static final String COLUMN_NAME_waza4 = "waza4";
    private static final String COLUMN_NAME_memo = "memo";
    private static final String COLUMN_NAME_seikaku = "seikaku";
    private static final String COLUMN_NAME_dougu = "dougu";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME_NAME + " TEXT," +
            COLUMN_NAME_ID + " TEXT," +
            COLUMN_NAME_tokusei + " TEXT," +
            COLUMN_NAME_level + " TEXT," +
            COLUMN_NAME_hpkotaiti + " TEXT," +
            COLUMN_NAME_atkotaiti + " TEXT," +
            COLUMN_NAME_dfkotaiti + " TEXT," +
            COLUMN_NAME_spatkotaiti + " TEXT," +
            COLUMN_NAME_spdfkotaiti + " TEXT," +
            COLUMN_NAME_spdkotaiti + " TEXT," +
            COLUMN_NAME_hpdoryokuti + " TEXT," +
            COLUMN_NAME_atdoryokuti + " TEXT," +
            COLUMN_NAME_dfdoryokuti+ " TEXT," +
            COLUMN_NAME_spatdoryokuti + " TEXT," +
            COLUMN_NAME_spdfdoryokuti + " TEXT," +
            COLUMN_NAME_spddoryokuti + " TEXT," +
            COLUMN_NAME_waza1 + " TEXT," +
            COLUMN_NAME_waza2 + " TEXT," +
            COLUMN_NAME_waza3 + " TEXT," +
            COLUMN_NAME_waza4 + " TEXT," +
            COLUMN_NAME_memo + " TEXT," +
            COLUMN_NAME_seikaku + " TEXT," +
            COLUMN_NAME_dougu + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS" + TABLE_NAME;

    MyOpenHelper(Context context) {
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
