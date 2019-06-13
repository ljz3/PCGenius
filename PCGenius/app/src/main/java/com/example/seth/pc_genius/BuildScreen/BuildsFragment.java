package com.example.seth.pc_genius.BuildScreen;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.seth.pc_genius.BuildObject.Build;
import com.example.seth.pc_genius.BuildObject.BuildsAdapter;
import com.example.seth.pc_genius.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildsFragment extends Fragment {
    protected static List<Build> buildList = new ArrayList<>();
    String buildTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Builds");

        final BuildsAdapter adapter = new BuildsAdapter(getActivity(), -1, buildList);
        View view = inflater.inflate(R.layout.fragment_builds, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.list_builds);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                InfoBuildDisplay infoBuildDisplay = new InfoBuildDisplay();
                bundle.putInt("Position", position);
                infoBuildDisplay.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.screen_area, infoBuildDisplay, null);
                transaction.addToBackStack(null);
                transaction.commit();
                Log.i("info", "item clicked");


            }
        });

        Log.i("info", "" + R.id.fab);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
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

                                        try {

                                            File file = new File(getActivity().getFilesDir().getPath() + "/builds.csv");

                                            FileOutputStream stream;
                                            // if file doesnt exists, then create it
                                            if (!file.exists()) {

                                                Log.d("EXISTS", "DNE");
                                                stream = new FileOutputStream(file);


                                            } else {
                                                Log.d("EXISTS", "EXISTS");
                                                stream = new FileOutputStream(file, true);

                                            }

                                            CharSequence cs = userInput.getText().toString() + " , ";
                                            String s = cs.toString();
                                            byte b[] = s.getBytes();
                                            stream.write(b);

                                            stream.close();
                                            stream.flush();


                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        readBuild();
                                        buildTitle = userInput.getText().toString();
                                        Log.i("info", "test");
                                        adapter.notifyDataSetChanged();


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

        readBuild();
        Log.i("info", "test");
        adapter.notifyDataSetChanged();

        return view;
    }


    private void readBuild() {


        try {

            File file = new File(getActivity().getFilesDir().getPath() + "/builds.csv");

            FileInputStream inputStream = new FileInputStream(file);

            String fullStr = "";
            int i = 0;
            while ((i = inputStream.read()) != -1) {

                char ch = (char) i;
                String str = String.valueOf(ch);
                //          Log.d("READ", str);
                fullStr += str;

            }
            Log.d("READ", fullStr);

            String[] savedParts = fullStr.split(",");

            for (int z = buildList.size(); z < savedParts.length - 1; z++) {
                File buildFile = new File(getActivity().getFilesDir().getPath() + "/" + savedParts[z] + ".csv");

                buildList.add(new Build(savedParts[z]));
                Log.d("BUILD NAME", savedParts[z]);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



