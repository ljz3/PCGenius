package com.example.seth.pc_genius.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.seth.pc_genius.data.PartContract.PartEntry.PART_TABLE_NAME;

public class DbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = DbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "books.db";

    private static final int DATABASE_VERSION = 1;

    public static final String COL2 = "TYPE";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PARTS_TABLE = "CREATE TABLE " + PART_TABLE_NAME + " ("
                + PartContract.PartEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PartContract.PartEntry.PART_TYPE + " TEXT NOT NULL DEFAULT 0, "
                + PartContract.PartEntry.PART_MODEL + "TEXT NOT NULL DEFAULT 0, "
                + PartContract.PartEntry.PART_PRICE + " DOUBLE NOT NULL DEFAULT 0, "
                + PartContract.PartEntry.PART_VENDOR + "TEXT, "
                + PartContract.PartEntry.PART_SCORE + " INTEGER, "
                + PartContract.PartEntry.PART_RANK + " INTEGER, "
                + PartContract.PartEntry.PART_SAMPLES + " TEXT, "
                + PartContract.PartEntry.PART_IMG+ "TEXT);";

        db.execSQL(SQL_CREATE_PARTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // The database is still at version 1, so there's nothing to do be done here.

    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + PART_TABLE_NAME, null);
        return data;
    }

    public boolean addData(String item1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);

        long result = db.insert(PART_TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}