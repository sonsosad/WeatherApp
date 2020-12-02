package com.vinid.myfirstproject.sunshine.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hungnm24 on 4/26/20
 * Copyright (c) {2020} VinID. All rights reserved.
 */

public class WeatherDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Weather.db";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + WeatherContract.WeatherEntry.TABLE_NAME + " (" +
                    WeatherContract.WeatherEntry._ID + " INTEGER PRIMARY KEY," +
                    WeatherContract.WeatherEntry.COLUMN_NAME_TYPE + " TEXT," +
                    WeatherContract.WeatherEntry.COLUMN_NAME_DATE + " TEXT," +
                    WeatherContract.WeatherEntry.COLUMN_NAME_MAX_TEMP + " TEXT," +
                    WeatherContract.WeatherEntry.COLUMN_NAME_MIN_TEMP + " TEXT)";

    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + WeatherContract.WeatherEntry.TABLE_NAME;

    public WeatherDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}