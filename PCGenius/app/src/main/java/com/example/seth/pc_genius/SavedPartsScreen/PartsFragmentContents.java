package com.example.seth.pc_genius.SavedPartsScreen;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.R;
import com.example.seth.pc_genius.data.PartContract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PartsFragmentContents extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private Uri mCurrentBookUri;

    public static void initPartsListParts(List<Part> list, Context context, List<Part> parts) {

        for(Part part: parts){
            list.add(new Part(part.getmModel(),part.getmType(),part.getmPrice(),R.drawable.image_icon));
        }

       // list.add(new Part("random", "Motherboard", 199.99, R.drawable.image_icon));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
/*
        String[] projection = {
                PartContract.PartEntry._ID,
                BookContract.BookEntry.BOOK_NAME,
                BookContract.BookEntry.BOOK_PRICE,
                BookContract.BookEntry.BOOK_QUANTITY,
                BookContract.BookEntry.SUPPLIER_NAME,
                BookContract.BookEntry.BOOK_PHONE};

        return new CursorLoader(this,
                PartEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
                */
return null;
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
