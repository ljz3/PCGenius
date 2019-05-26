package com.example.seth.pc_genius.PartObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seth.pc_genius.R;

import java.util.List;

public class PartAdapter extends ArrayAdapter<Part> {

    public PartAdapter(Context context, int resources, List<Part> locationsList) {
        super(context, 0, locationsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.listview_activity, parent, false);
        }
        Part currentLocation = getItem(position);

        if(currentLocation.getmBitmap()!=null){
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.listview_image);
            imageView.setImageBitmap(currentLocation.getmBitmap());
        }else {
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.listview_image);
            imageView.setImageResource(currentLocation.getmImageResourceId());
        }

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.partItemModel);
        nameTextView.setText(currentLocation.getmName());

        TextView priceTextView = (TextView) listItemView.findViewById(R.id.partItemPrice);
        priceTextView.setText(currentLocation.getmPrice() + "$");

        TextView descTextView = (TextView) listItemView.findViewById(R.id.partItemVendor);
        descTextView.setText(("Description:   " + currentLocation.getmDescription()));


        return listItemView;
    }
}
