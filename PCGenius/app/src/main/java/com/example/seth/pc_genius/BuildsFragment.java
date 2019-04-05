package com.example.seth.pc_genius;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SimpleTimeZone;

public class BuildsFragment extends Fragment {
    final Context context = this.getContext();
    private Button button;
    private EditText result;
    String buildTitle;
    List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
    SimpleAdapter simpleAdapter;


    String[] listviewTitle = new String[]{
            "ListView Title 1", "ListView Title 2", "ListView Title 3", "ListView Title 4", "ListView Title 5",
            "ListView Title 6", "ListView Title 7", "ListView Title 8", "ListView Title 9", "ListView Title 10",
    };


    int[] listviewImage = new int[]{
            R.drawable.image_icon, R.drawable.image_icon, R.drawable.image_icon, R.drawable.image_icon, R.drawable.image_icon,
            R.drawable.image_icon, R.drawable.image_icon, R.drawable.image_icon, R.drawable.image_icon, R.drawable.image_icon,
    };

    String[] listviewShortDescription = new String[]{
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
    };

    double[] listviewprice = new double[]{
            1000.00, 100.00, 100.00, 100.00, 100.00, 100.00, 100.00, 100.00, 100.00, 100.00
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_builds, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Builds");
        button = (Button) getView().findViewById(R.id.button);


        for (int i = 0; i < 10; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            hm.put("listview_price", "$" + Double.toString(listviewprice[i]));
            aList.add(hm);
        }


        String[] from = {"listview_image", "listview_title", "listview_discription", "listview_price"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description, R.id.listview_item_price};

        simpleAdapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.listview_activity, from, to);
        ListView androidListView = getView().findViewById(R.id.list_builds);
        androidListView.setAdapter(simpleAdapter);

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(getContext());
                View promptsView = li.inflate(R.layout.prompts, null);


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getContext());

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        buildTitle = userInput.getText().toString();

                                        HashMap<String, String> hm = new HashMap<String, String>();
                                        hm.put("listview_title", buildTitle);
                                        hm.put("listview_discription", "");
                                        hm.put("listview_image", Integer.toString(R.drawable.image_icon));
                                        hm.put("listview_price", "$" + 0.00);
                                        aList.add(hm);
                                        simpleAdapter.notifyDataSetChanged();

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

    }
}



