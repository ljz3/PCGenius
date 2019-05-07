package com.example.seth.pc_genius.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = DbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "books.db";

    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE " + PartContract.BookEntry.BOOK_TABLE_NAME + " ("
                + PartContract.BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PartContract.BookEntry.BOOK_NAME + " TEXT NOT NULL DEFAULT 0, "
                + PartContract.BookEntry.BOOK_PRICE + " DOUBLE NOT NULL DEFAULT 0, "
                + PartContract.BookEntry.BOOK_QUANTITY + " INTEGER NOT NULL, "
                + PartContract.BookEntry.SUPPLIER_NAME + " TEXT, "
                + PartContract.BookEntry.BOOK_PHONE + " TEXT);";
        db.execSQL(SQL_CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // The database is still at version 1, so there's nothing to do be done here.

    }
}