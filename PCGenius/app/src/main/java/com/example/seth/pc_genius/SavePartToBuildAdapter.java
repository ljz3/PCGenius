package com.example.seth.pc_genius;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.seth.pc_genius.BuildObject.Build;

import java.util.List;

public class SavePartToBuildAdapter extends ArrayAdapter<String> {


    public SavePartToBuildAdapter(Context context, int resources, List<String> locationsList) {
        super(context, 0, locationsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.listview_activity, parent, false);
        }

        String currentLocation = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.partItemModel);
        nameTextView.setText(currentLocation);

        return listItemView;
    }

}
