package com.example.myfirstapplication.database_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager extends SQLiteOpenHelper {

    //Database Name and Version
    public static final String DB_NAME = "MyCountriesDB";
    public static final int DB_VERSION = 1;

    //Table Name
    public static final String TABLE_NAME = "COUNTRIES";

    //Table Columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";


    //Create Table
    private static final String CREATE_TABLE = "create table "+ TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ NAME + " TEXT NOT NULL, "+ DESCRIPTION +" TEXT);";

    public DbManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
