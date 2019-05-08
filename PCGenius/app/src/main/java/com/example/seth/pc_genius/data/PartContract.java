package com.example.seth.pc_genius.data;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class PartContract {

    public static final String CONTENT_AUTHORITY = "com.example.seth.pc_genius";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PARTS = "parts";

    private PartContract() {
    }

    public static final class PartEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PARTS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PARTS;


        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PARTS;

        public final static String PART_TABLE_NAME = "parts";

        public final static String _ID = BaseColumns._ID;

        public final static String PART_NAME = "name";

        public final static String PART_PRICE = "price";

        public final static String PART_PHONE = "phone";

        public final static String PART_QUANTITY = "qty";

        public final static String SUPPLIER_NAME = "supname";

    }
}