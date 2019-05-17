package com.example.seth.pc_genius.data;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.seth.pc_genius.data.PartContract.PartEntry;
public class PartProvider extends ContentProvider {

    public static final String LOG_TAG = PartProvider.class.getSimpleName();

    private static final int PARTS = 100;

    private static final int PART_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        sUriMatcher.addURI(PartContract.CONTENT_AUTHORITY, PartContract.PATH_PARTS, PARTS);

        sUriMatcher.addURI(PartContract.CONTENT_AUTHORITY, PartContract.PATH_PARTS + "/#", PART_ID);
    }

    private DbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new DbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case PARTS:

                cursor = database.query(PartEntry.PART_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case PART_ID:

                selection = PartEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(PartEntry.PART_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PARTS:
                return insertPart(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertPart(Uri uri, ContentValues values) {
        String name = values.getAsString(PartEntry.PART_TYPE);
        if (name == null) {
            throw new IllegalArgumentException("Part requires a name");
        }


        Integer qty = values.getAsInteger(PartEntry.PART_SCORE);
        if (qty != null && qty < 0) {
            throw new IllegalArgumentException("Part requires valid quantity");
        }

        Double price = values.getAsDouble(PartEntry.PART_PRICE);
        if (price != null && price < 0) {
            throw new IllegalArgumentException("Part requires valid price");
        }

        String supplier = values.getAsString(PartEntry.PART_TYPE);
        if (supplier == null) {
            throw new IllegalArgumentException("Part requires a supplier");
        }
        String phone = values.getAsString(PartEntry.PART_TYPE);
        if (phone == null) {
            throw new IllegalArgumentException("Part requires a supplier phone number");
        }


        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(PartEntry.PART_TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PARTS:
                return updatePart(uri, contentValues, selection, selectionArgs);
            case PART_ID:

                selection = PartEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePart(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }


    private int updatePart(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(PartEntry.PART_TYPE)) {
            String name = values.getAsString(PartEntry.PART_TYPE);
            if (name == null) {
                throw new IllegalArgumentException("Part requires a name");
            }
        }

        if (values.containsKey(PartEntry.PART_SCORE)) {
            Integer qty = values.getAsInteger(PartEntry.PART_SCORE);
            if (qty != null && qty < 0) {
                throw new IllegalArgumentException("Part requires valid quantity");
            }
        }

        if (values.containsKey(PartEntry.PART_PRICE)) {
            Double price = values.getAsDouble(PartEntry.PART_PRICE);
            if (price != null && price < 0) {
                throw new IllegalArgumentException("Part requires valid price");
            }
        }

        if (values.containsKey(PartEntry.PART_TYPE)) {
            String supplier = values.getAsString(PartEntry.PART_TYPE);
            if (supplier == null) {
                throw new IllegalArgumentException("Part requires a supplier");
            }
        }

        if (values.containsKey(PartEntry.PART_TYPE)) {
            String phone = values.getAsString(PartEntry.PART_TYPE);
            if (phone == null) {
                throw new IllegalArgumentException("Part requires a supplier phone number");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PartEntry.PART_TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PARTS:
                // Delete all rows that match the selection and selection args
                rowsDeleted = database.delete(PartEntry.PART_TABLE_NAME, selection, selectionArgs);
                break;
            case PART_ID:
                // Delete a single row given by the ID in the URI
                selection = PartEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PartEntry.PART_TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PARTS:
                return PartEntry.CONTENT_LIST_TYPE;
            case PART_ID:
                return PartEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}
