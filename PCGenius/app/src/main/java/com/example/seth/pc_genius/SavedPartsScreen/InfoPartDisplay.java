package com.example.seth.pc_genius.SavedPartsScreen;


import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
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
    private String mBenchmark;
    private Bitmap mBitmap=null;


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.part_info_display, container, false);
        mName = getArguments().getString("Name", "");
        mDescription = getArguments().getString("Description", "");
        mPrice = getArguments().getDouble("Price", 0);
        mImageResourceId = getArguments().getInt("ImageResource", 0);
        mVendor = getArguments().getString("Vendor","");
        mBenchmark= getArguments().getString("Bench","N/A");
        try {
            mBitmap = getArguments().getParcelable("BitmapImage");

        } catch (Exception e) {
            e.printStackTrace();
        }


        TextView partDisplay = view.findViewById(R.id.partInfoNameDisplay);
        partDisplay.setText(mName);
        TextView descriptionDisplay = view.findViewById(R.id.partInfoDescriptionDisplay);
        descriptionDisplay.setText(mDescription);
        TextView priceDisplay = view.findViewById(R.id.partInfoPriceDisplay);
        priceDisplay.setText(Double.toString(mPrice));
        ImageView imageDisplay = view.findViewById(R.id.partInfoImageDisplay);
        TextView benchmarkDisplay = view.findViewById(R.id.benchmarkDisplay);
        benchmarkDisplay.setText(mBenchmark);
        if(mBitmap!=null){
            imageDisplay.setImageBitmap(mBitmap);
        }else {
            imageDisplay.setImageResource(mImageResourceId);
        }

        return view;
    }

}
