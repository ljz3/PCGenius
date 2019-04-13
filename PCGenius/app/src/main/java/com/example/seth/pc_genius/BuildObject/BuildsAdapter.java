package com.example.seth.pc_genius.BuildObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.R;

import java.util.ArrayList;
import java.util.List;

public class BuildsAdapter extends ArrayAdapter<Build> {


    public BuildsAdapter(Context context, int resources, List<Build> locationsList) {
        super(context, 0, locationsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.listview_activity, parent, false);
        }
        Build currentLocation = getItem(position);

       /* ImageView imageView = (ImageView) listItemView.findViewById(R.id.listview_image);
        imageView.setImageResource(currentLocation.getmImageResourceId());*/


        TextView nameTextView = (TextView) listItemView.findViewById(R.id.listview_item_title);
        nameTextView.setText(currentLocation.getmName());

        TextView priceTextView = (TextView) listItemView.findViewById(R.id.listview_item_price);
        priceTextView.setText(currentLocation.getmPrice() + "$");

        TextView descTextView = (TextView) listItemView.findViewById(R.id.listview_item_short_description);
        descTextView.setText(("Description:   " + currentLocation.getmDescription()));


        return listItemView;
    }

}