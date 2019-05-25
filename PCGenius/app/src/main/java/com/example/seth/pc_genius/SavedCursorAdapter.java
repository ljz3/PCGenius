package com.example.seth.pc_genius;

import android.widget.CursorAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class SavedCursorAdapter extends CursorAdapter {

    public SavedCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.part_info_display, parent, false);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView nameTextView = (TextView) view.findViewById(R.id.partInfoNameDisplay);
        TextView descTextView = (TextView) view.findViewById(R.id.partInfoDescriptionDisplay);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        TextView supplierTextView = (TextView) view.findViewById(R.id.supplier);
        TextView phoneTextView = (TextView) view.findViewById(R.id.phone);
        Button saleButton = (Button) view.findViewById(R.id.sale_button);

        int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(BookEntry.BOOK_NAME);
        int qtyColumnIndex = cursor.getColumnIndex(BookEntry.BOOK_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.BOOK_PRICE);
        int supplierColumnIndex = cursor.getColumnIndex(BookEntry.SUPPLIER_NAME);
        int phoneColumnIndex = cursor.getColumnIndex(BookEntry.BOOK_PHONE);

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
