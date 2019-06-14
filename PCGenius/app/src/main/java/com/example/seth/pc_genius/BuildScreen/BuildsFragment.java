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
import com.example.seth.pc_genius.PartObject.Part;
import com.example.seth.pc_genius.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildsFragment extends Fragment {
    public static List<Build> buildList = new ArrayList<>();
    String buildTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Builds");

        Log.d("BUILDLISTSIZESTART", String.valueOf(buildList.size()));

        final BuildsAdapter adapter = new BuildsAdapter(getActivity(), -1, buildList);
        Log.d("BUILDLISTSIZESTART2", String.valueOf(buildList.size()));

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
                readBuild();

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

                                            CharSequence cs = userInput.getText().toString() + ",";
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
            Log.d("READFULLSTR", fullStr);

            String[] buildNames = fullStr.split(",");

            Build build;
            Log.d("LMAO", String.valueOf(buildList.size()));
            Log.d("NOWBUILD NAME", buildNames[0]);

            for (int z = 0; z < buildNames.length; z++) {
                build = new Build(buildNames[z]);
               buildList.add(addPartsToBuild(build));
                Log.d("NOWBUILD NAME", buildNames[z]);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Build addPartsToBuild(Build build) {

        Log.d("IS CALL", "CALL");


        String name = build.getmName();
        try {

            File file = new File(getActivity().getFilesDir().getPath() + "/" + name + ".csv");


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

            String[] savedParts = fullStr.split("@");
            Log.d("SAVEINFOLENGTH", String.valueOf(savedParts.length));

            for(String savedP:savedParts){

                String[] savedInfo = savedP.split(",");
                Log.d("SAVEINFOLENGTH", String.valueOf(savedInfo.length));

                Part part = new Part();


                if(savedInfo.length!=0) {
                    part.setmModel(savedInfo[0]);
                    Log.d("model", savedInfo[0]);

                    part.setmVendor(savedInfo[1]);
                    Log.d("vendor", savedInfo[1]);

                    part.setmPrice(Double.parseDouble(savedInfo[2]));
                    Log.d("price", savedInfo[2]);


                    part.setmBenchmark(savedInfo[3]);
                    Log.d("bench", savedInfo[3]);

                    build.addPart(part);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
        Log.d("ACTUAL BUILD NAME", name.replaceAll("\\s+", ""));

        try {

            File buildFile = new File(getActivity().getFilesDir().getPath() + "/" + name.replaceAll("\\s+", "") + ".csv");

            Log.e("DIRECTORY", getActivity().getFilesDir().getPath() + "/" + name.replaceAll("\\s+", "") + ".csv");
            FileInputStream inputStream = new FileInputStream(buildFile);

            String fullStr = "";
            int i = 0;
            while ((i = inputStream.read()) != -1) {

                char ch = (char) i;
                String str = String.valueOf(ch);
                //          Log.d("READ", str);
                fullStr += str;

            }
            Log.d("READ", fullStr);

            String[] savedParts = fullStr.split("@");


            for (String savedP : savedParts) {

                String[] savedInfo = savedP.split(",");
                Part part = new Part();

                if (savedInfo.length != 0) {
                    part.setmModel(savedInfo[0]);
                    Log.d("ACTUALmodel", savedInfo[0]);

                    part.setmVendor(savedInfo[1]);
                    Log.d("ACTUALvendor", savedInfo[1]);

                    part.setmPrice(Double.parseDouble(savedInfo[2]));
                    Log.d("ACTUALprice", savedInfo[2]);

                    part.setmBenchmark(savedInfo[3]);
                    Log.d("ACTUALbench", savedInfo[3]);

                    build.addPart(part);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        return build;

    }

}



