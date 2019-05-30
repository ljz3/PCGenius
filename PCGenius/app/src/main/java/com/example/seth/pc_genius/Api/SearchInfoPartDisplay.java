package com.example.seth.pc_genius.Api;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.seth.pc_genius.PartObject.PartAdapter;
import com.example.seth.pc_genius.R;

import java.util.ArrayList;

import static com.example.seth.pc_genius.Api.SearchFragment.adapter;
import static com.example.seth.pc_genius.Api.SearchFragment.searchList;
import static com.example.seth.pc_genius.Api.SearchFragment.searchRelatedList;

public class SearchInfoPartDisplay extends Fragment {

    private String mName;
    private String mDescription;
    private double mPrice;
    private int mImageResourceId;
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


        View view = inflater.inflate(R.layout.search_part_info_display, container, false);
        mName = getArguments().getString("Name", "");
        mDescription = getArguments().getString("Description", "");
        mPrice = getArguments().getDouble("Price", 0);
        mImageResourceId = getArguments().getInt("ImageResource", 0);
        try {
            mBitmap = getArguments().getParcelable("BitmapImage");
        } catch (Exception e) {
            e.printStackTrace();
        }



        TextView partDisplay = view.findViewById(R.id.partDisplay);
        partDisplay.setText(mName);
        TextView descriptionDisplay = view.findViewById(R.id.descriptionDisplay);
        descriptionDisplay.setText(mDescription);
        TextView priceDisplay = view.findViewById(R.id.priceDisplay);
        priceDisplay.setText(Double.toString(mPrice));
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



