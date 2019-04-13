package com.example.seth.pc_genius.BuildScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.seth.pc_genius.PartObject.PartAdapter;
import com.example.seth.pc_genius.R;
import com.example.seth.pc_genius.SavedPartsScreen.PartsFragmentContents;

import java.util.ArrayList;

import static com.example.seth.pc_genius.BuildScreen.BuildsFragment.buildList;


public class InfoBuildDisplay extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     Log.i("info","infoBuild built");
        View view = inflater.inflate(R.layout.build_info_display, container, false);

        PartsFragmentContents.initPartsListParts(buildList.get(getArguments().getInt("Position", -1)).getmParts(), getContext());

        PartAdapter adapter = new PartAdapter(getActivity(), -1, buildList.get(getArguments().getInt("Position", -1)).getmParts());

        ListView listView = (ListView) view.findViewById(R.id.list_parts);

        listView.setAdapter(adapter);

        return view;
    }
}
