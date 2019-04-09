package com.example.seth.pc_genius.BuildScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.seth.pc_genius.Part;
import com.example.seth.pc_genius.SavedPartsScreen.PartAdapter;
import com.example.seth.pc_genius.R;

import java.util.ArrayList;
import java.util.List;

public class BuildsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        List<Part> list = new ArrayList<>();
        BuildsFragmentContents.initPartsListBuilds(list, getContext());

        PartAdapter adapter = new PartAdapter(getActivity(), -1, list);
        View view = inflater.inflate(R.layout.fragment_builds, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_builds);

        listView.setAdapter(adapter);

        return view;
    }
}



