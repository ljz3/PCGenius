package com.example.seth.pc_genius.Api;


import android.content.ContentValues;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.PartObject.PartAdapter;
import com.example.seth.pc_genius.R;
import com.example.seth.pc_genius.data.PartContract;
import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.seth.pc_genius.Api.SearchFragment.searchRelatedList;

public class SearchInfoPartDisplay extends Fragment {

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_save_part:
                savePart();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }



    private void savePart() {

       // EditText fileNameEdit= (EditText) getActivity().findViewById(R.raw.parts);
        String fileName = "parts";
            for(int i = 0; i<100; i++)
                Log.d("PATH IS", getActivity().getFilesDir().getPath());

            try {

                File file = new File( getActivity().getFilesDir().getPath()+"/parts.csv");

                FileOutputStream stream;
                // if file doesnt exists, then create it
                if (!file.exists()) {

                    Log.d("EXISTS", "DNE");
                    stream = new FileOutputStream(file);


                }else{
                    Log.d("EXISTS", "EXISTS");
                    stream = new FileOutputStream(file,true);

                }

                TextView name = (TextView) getView().findViewById(R.id.partNameDisplay);
                TextView vendor = (TextView) getView().findViewById(R.id.vendorDisplay);
                CharSequence cs = name.getText() + "," + vendor.getText() + "@";
                String s = cs.toString();
                byte b[]=s.getBytes();
                stream.write(b);

                stream.close();
                stream.flush();

                for(int i = 0; i<100; i++)
                    Log.d("TEST", "SUCCESS");

                FileInputStream inputStream = new FileInputStream(file);


                int i = 0;
                while((i=inputStream.read())!=-1){

                    char ch = (char)i;
                    String str = String.valueOf(ch);
                    Log.d("READ", str);

                }

      //      inputStream.close();
            //        FileWriter fw = new FileWriter(file.getAbsoluteFile());
      //      BufferedWriter bw = new BufferedWriter(fw);
        //    bw.write(content);
          //  bw.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("?","this failed");

        }



        Toast.makeText(getActivity(),
                "Saved", Toast.LENGTH_LONG).show();


        /*
        TextView partNameDisplay = (TextView) getView().findViewById(R.id.partNameDisplay);
        String nameString = partNameDisplay.getText().toString().trim();
        ContentValues values = new ContentValues();

        values.put(PartContract.PartEntry.PART_MODEL, nameString);
        values.put(PartContract.PartEntry.PART_PRICE, 2);
        values.put(PartContract.PartEntry.PART_TYPE, "GPU");
*/


        /*
        mPriceEditText = (EditText) findViewById(R.id.edit_book_price);
        mQtyEditText = (EditText) findViewById(R.id.edit_book_qty);
        mSupEditText = (EditText) findViewById(R.id.edit_book_sup);
        mPhoneEditText = (EditText) findViewById(R.id.edit_book_phone);

        String nameString = partNameDisplay.getText().toString().trim();
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
*/
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.search_part_info_display, container, false);
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



        TextView partDisplay = view.findViewById(R.id.partNameDisplay);
        partDisplay.setText(mName);
        TextView descriptionDisplay = view.findViewById(R.id.descriptionDisplay);
        descriptionDisplay.setText(mDescription);
        TextView priceDisplay = view.findViewById(R.id.priceDisplay);
        priceDisplay.setText(Double.toString(mPrice));
        TextView vendorDisplay = view.findViewById(R.id.vendorDisplay);
        vendorDisplay.setText(mVendor);
        ImageView imageDisplay = view.findViewById(R.id.imageDisplay);

        if(mBitmap!=null){
            imageDisplay.setImageBitmap(mBitmap);
        }else {
            imageDisplay.setImageResource(mImageResourceId);
        }


        Log.i("importantInfo", "test");
        PartAdapter adapter = new PartAdapter(getActivity(), -1, searchRelatedList);
        ListView listView = (ListView) view.findViewById(R.id.relatedParts);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                SearchInfoPartDisplay searchInfoPartDisplay = new SearchInfoPartDisplay();
                bundle.putString("Name", searchRelatedList.get(position).getmName());
                bundle.putString("Description", searchRelatedList.get(position).getmDescription());
                bundle.putInt("ImageResource", searchRelatedList.get(position).getmImageResourceId());
                bundle.putDouble("Price", searchRelatedList.get(position).getmPrice());
                bundle.putParcelable("BitmapImage",searchRelatedList.get(position).getmBitmap());
                bundle.putString("Vendor",searchRelatedList.get(position).getmVendor());
                searchInfoPartDisplay.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.screen_area, searchInfoPartDisplay, null);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });

        return view;
    }
    }



