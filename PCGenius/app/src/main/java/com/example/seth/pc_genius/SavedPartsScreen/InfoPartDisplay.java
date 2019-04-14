package com.example.seth.pc_genius.SavedPartsScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seth.pc_genius.R;


public class InfoPartDisplay extends Fragment {
    private String mName;
    private String mDescription;
    private double mPrice;
    private int mImageResourceId;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.part_info_display, container, false);
        mName = getArguments().getString("Name", "");
        mDescription = getArguments().getString("Description", "");
        mPrice = getArguments().getDouble("Price", 0);
        mImageResourceId = getArguments().getInt("ImageResource", 0);

        TextView partDisplay = view.findViewById(R.id.partDisplay);
        partDisplay.setText(mName);
        TextView descriptionDisplay = view.findViewById(R.id.descriptionDisplay);
        descriptionDisplay.setText(mDescription);
        TextView priceDisplay = view.findViewById(R.id.priceDisplay);
        priceDisplay.setText(Double.toString(mPrice));
        ImageView imageDisplay = view.findViewById(R.id.imageDisplay);
        imageDisplay.setImageResource(mImageResourceId);


        return view;
    }

}
