package com.example.seth.pc_genius.BuildScreen;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.example.seth.pc_genius.PartObject.PartAdapter;
import com.example.seth.pc_genius.R;
import com.example.seth.pc_genius.SavedPartsScreen.InfoPartDisplay;

import java.util.ArrayList;
import java.util.List;

public class BuildsFragment extends Fragment {
    String buildTitle;
    protected static List<Build> buildList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Builds");
        buildList = new ArrayList<>();
        BuildsFragmentContents.initPartsListBuilds(buildList, getContext());

        final BuildsAdapter adapter = new BuildsAdapter(getActivity(), -1, buildList);
        View view = inflater.inflate(R.layout.fragment_builds, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.list_builds);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Bundle bundle = new Bundle();
               InfoBuildDisplay infoBuildDisplay = new InfoBuildDisplay();
                bundle.putInt("Position", position);
                infoBuildDisplay.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.screen_area, infoBuildDisplay, null);
                transaction.addToBackStack(null);
                transaction.commit();
                Log.i("info","item clicked");


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
                                        buildTitle = userInput.getText().toString();
                                        buildList.add(new Build(buildTitle));
                                        Log.i("info","test");
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


        return view;
    }





}



