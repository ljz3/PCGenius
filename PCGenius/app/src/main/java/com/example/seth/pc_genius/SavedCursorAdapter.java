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
        int phoneColumnIndex = cursor.getColumnIndex(PartContract.PartEntry.BOOK_PHONE);

        final int bookID = cursor.getInt(idColumnIndex);
        String bookName = cursor.getString(nameColumnIndex);
        final String bookQty = cursor.getString(qtyColumnIndex);
        String bookPrice = cursor.getString(priceColumnIndex);
        String bookSupplier = cursor.getString(supplierColumnIndex);
        String bookPhone = cursor.getString(phoneColumnIndex);
        final int bookQuantity = cursor.getInt(qtyColumnIndex);



        nameTextView.setText("Name: " + bookName);
        descTextView.setText("Quantity: " + bookQty);
        priceTextView.setText(" Price: " + bookPrice);
        supplierTextView.setText("Supplier Name: " + bookSupplier);
        phoneTextView.setText("Supplier Phone: " + bookPhone);

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatalogActivity activity = (CatalogActivity) context;
                activity.sellBook(bookID, bookQuantity);
            }
        });

    }
}
