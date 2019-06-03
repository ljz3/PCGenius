package com.example.seth.pc_genius;

import android.widget.CursorAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.seth.pc_genius.data.PartContract;

public class SavedCursorAdapter extends CursorAdapter {

    public SavedCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listview_activity, parent, false);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView nameTextView = (TextView) view.findViewById(R.id.partItemModel);
        TextView descTextView = (TextView) view.findViewById(R.id.partItemVendor);
        TextView priceTextView = (TextView) view.findViewById(R.id.partItemPrice);

        int idColumnIndex = cursor.getColumnIndex(PartContract.PartEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(PartContract.PartEntry.PART_MODEL);
        int priceColumnIndex = cursor.getColumnIndex(PartContract.PartEntry.PART_PRICE);
        int supplierColumnIndex = cursor.getColumnIndex(PartContract.PartEntry.PART_VENDOR);

        String partName = cursor.getString(nameColumnIndex);


        nameTextView.setText(partName);


    }
}
