package com.example.seth.pc_genius.SavedPartsScreen;


import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seth.pc_genius.Api.SearchRelatedPart;
import com.example.seth.pc_genius.R;


public class InfoPartDisplay extends Fragment {
    private String mName;
    private String mDescription;
    private double mPrice;
    private int mImageResourceId;
    private String mVendor;
    private Bitmap mBitmap=null;


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_part_info, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.part_info_display, container, false);
        mName = getArguments().getString("Name", "");
        mDescription = getArguments().getString("Description", "");
        mPrice = getArguments().getDouble("Price", 0);
        mImageResourceId = getArguments().getInt("ImageResource", 0);
        mVendor = getArguments().getString("Vendor","");
        try {
            mBitmap = getArguments().getParcelable("BitmapImage");
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        TextView partDisplay = view.findViewById(R.id.partInfoNameDisplay);

        partDisplay.setText(mName);
        TextView descriptionDisplay = view.findViewById(R.id.partInfoDescriptionDisplay);
        descriptionDisplay.setText(mDescription);
        TextView priceDisplay = view.findViewById(R.id.priceDisplay);
        //priceDisplay.setText(Double.toString(mPrice));
        ImageView imageDisplay = view.findViewById(R.id.partInfoImageDisplay);
        if(mBitmap!=null){
            imageDisplay.setImageBitmap(mBitmap);
        }else {
            imageDisplay.setImageResource(mImageResourceId);
        }

        return view;
    }



/*
    private void saveBook() {

        String nameString = mNameEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();
        String qtyString = mQtyEditText.getText().toString().trim();
        String supString = mSupEditText.getText().toString().trim();
        String phoneString = mPhoneEditText.getText().toString().trim();


        if (mCurrentBookUri == null &&
                TextUtils.isEmpty(nameString) && TextUtils.isEmpty(priceString) &&
                TextUtils.isEmpty(supString) &&
                TextUtils.isEmpty(phoneString)) {
            return;
        }

        if (TextUtils.isEmpty(nameString)) {
            return;
        }
        if (TextUtils.isEmpty(priceString)) {
            return;
        }
        if (TextUtils.isEmpty(qtyString)) {
            return;
        }
        if (TextUtils.isEmpty(supString)) {
            return;
        }
        if (TextUtils.isEmpty(phoneString)) {
            return;
        }



        double price = Double.parseDouble(priceString);

        ContentValues values = new ContentValues();
        values.put(BookEntry.BOOK_NAME, nameString);
        values.put(BookEntry.BOOK_PRICE, price);
        values.put(BookEntry.SUPPLIER_NAME, supString);
        values.put(BookEntry.BOOK_PHONE, phoneString);

        int qty = 0;
        if (!TextUtils.isEmpty(qtyString)) {
            qty = Integer.parseInt(qtyString);
        }
        values.put(BookEntry.BOOK_QUANTITY, qty);

        if (mCurrentBookUri == null) {

            Uri newUri = getContentResolver().insert(BookContract.BookEntry.CONTENT_URI, values);

            if (newUri == null) {
                Toast.makeText(this, getString(R.string.editor_insert_book_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_insert_book_successful),
                        Toast.LENGTH_SHORT).show();
            }
        } else {

            int rowsAffected = getContentResolver().update(mCurrentBookUri, values, null, null);

            if (rowsAffected == 0) {

                Toast.makeText(this, getString(R.string.editor_update_book_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.editor_update_book_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
*/
}
