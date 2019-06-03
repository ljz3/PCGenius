package com.example.seth.pc_genius.SavedPartsScreen;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.R;
import com.example.seth.pc_genius.SavedCursorAdapter;
import com.example.seth.pc_genius.data.PartContract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PartsFragmentContents extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private Uri mCurrentBookUri;
    static SavedCursorAdapter mCursorAdapter;

    public static void initPartsListParts(List<Part> list, Context context, List<Part> parts) {

        ListView savedListView = (ListView) findViewById(R.id.listSavedParts);

        // Setup an Adapter to create a list item for each row of pet data in the Cursor.
        // There is no pet data yet (until the loader finishes) so pass in null for the Cursor.
        mCursorAdapter = new SavedCursorAdapter(this, null);
        savedListView.setAdapter(mCursorAdapter);


        // Kick off the loader
        getLoaderManager().initLoader(0, null, this);

        /*
        for(Part part: parts){
            list.add(new Part(part.getmModel(),part.getmType(),part.getmPrice(),R.drawable.image_icon));
        }
*/
       // list.add(new Part("random", "Motherboard", 199.99, R.drawable.image_icon));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] projection = {
                PartContract.PartEntry._ID,};

        return new CursorLoader(this,
                PartContract.PartEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);

    }
}
