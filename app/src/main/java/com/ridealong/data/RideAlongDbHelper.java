package com.ridealong.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sridhar16 on 5/26/2016.
 */
public class RideAlongDbHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2;
    static final String DATABASE_NAME = "ride-along.db";

    public RideAlongDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_PASSEGR_TRAVEL_INFO_TABLE = "CREATE TABLE " + Contract.PassgrTravelInfoEntry.TABLE_NAME + " (" +
                Contract.PassgrTravelInfoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Contract.PassgrTravelInfoEntry.COLUMN_FIRST_NAME + " TEXT NOT NULL," +
                Contract.PassgrTravelInfoEntry.COLUMN_FROM_CITY + " TEXT NOT NULL," +
                Contract.PassgrTravelInfoEntry.COLUMN_TO_CITY + " TEXT NOT NULL," +
                Contract.PassgrTravelInfoEntry.COLUMN_DATE + " TEXT NOT NULL," +
                Contract.PassgrTravelInfoEntry.COLUMN_TIME + " TEXT NOT NULL );";
        db.execSQL(SQL_CREATE_PASSEGR_TRAVEL_INFO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Contract.PassgrTravelInfoEntry.TABLE_NAME);
        onCreate(db);
    }
}
